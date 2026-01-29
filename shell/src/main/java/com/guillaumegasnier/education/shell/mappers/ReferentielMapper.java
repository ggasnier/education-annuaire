package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.shell.datasets.CODESNSF;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.NOMENCLATUREEUROPE;
import com.guillaumegasnier.education.shell.datasets.OuiNonType;
import com.guillaumegasnier.education.shell.dto.referentiels.CertificationDTO;
import com.guillaumegasnier.education.shell.dto.referentiels.NSFDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ReferentielMapper {

    @Named("toActif")
    public Boolean toActif(OuiNonType ouiNonType) {
        return OuiNonType.OUI.equals(ouiNonType);
    }

    @Named("toNSFList")
    public List<String> toNSFList(@NonNull CODESNSF nsf) {
        return nsf.getNSF().stream().map(CODESNSF.NSF::getCODE).toList();
    }

    @Named("toNiveau")
    public int toNiveau(NOMENCLATUREEUROPE nomenclatureeurope) {
        String s = nomenclatureeurope.getNIVEAU();
        if (s == null || s.length() < 4) return -1;
        char c = s.charAt(s.length() - 1);
        return (c >= '1' && c <= '8') ? (c - '0') : -1;
    }

    @Mapping(target = "niveau", source = "NOMENCLATUREEUROPE", qualifiedByName = "toNiveau")
    @Mapping(target = "nsfList", source = "CODESNSF", qualifiedByName = "toNSFList")
    @Mapping(target = "nouveauCode", ignore = true)
    @Mapping(target = "actif", source = "ACTIF", qualifiedByName = "toActif")
    @Mapping(target = "nom", source = "INTITULE")
    @Mapping(target = "code", source = "NUMEROFICHE")
    public abstract CertificationDTO toCertificationDTO(FICHES.FICHE fiche);

    public List<NSFDTO> toNSFDTO(@NonNull List<CODESNSF.NSF> nsf) {
        return nsf.stream().map(nsf1 -> new NSFDTO(nsf1.getCODE(), nsf1.getLIBELLE())).toList();
    }
}
