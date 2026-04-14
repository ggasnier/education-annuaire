package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.exceptions.EtablissementAlreadyExistsException;
import com.guillaumegasnier.education.web.dto.etablissements.EtablissementDetailsDto;
import com.guillaumegasnier.education.web.dto.etablissements.IndicateurValeurAjouteeDTO;
import com.guillaumegasnier.education.web.dto.etablissements.IndicesPositionSocialeDTO;
import com.guillaumegasnier.education.web.dto.etablissements.NatureDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebEtablissementServiceImpl implements WebEtablissementService {

    private final CoreEtablissementService coreEtablissementService;
    private final CoreFormationService coreFormationService;
    private final CoreTerritoireService coreTerritoireService;
    private final WebEtablissementMapper webEtablissementMapper;

    @Override
    public Optional<EtablissementDto> findEtablissementByUai(String uai) {
        return coreEtablissementService.findEtablissement(uai).map(webEtablissementMapper::toEtablissementDto);
    }

    @Override
    public Optional<EtablissementDto> createEtablissement(@NonNull EtablissementRequestDto dto) {
        if (coreEtablissementService.findEtablissement(dto.getUai()).isPresent()) {
            throw new EtablissementAlreadyExistsException(dto.getUai());
        }

        EtablissementEntity entity = new EtablissementEntity();
        entity.setUai(dto.getUai());
        entity.setNom(dto.getNom());
        entity.setAdresse(dto.getAdresse());
        entity.setComplement(dto.getComplement());
        entity.setCodePostal(dto.getCodePostal());

        if (dto.getCodeNature() != null) {
            coreEtablissementService.findNature(dto.getCodeNature()).ifPresent(entity::setNature);
        }
        if (dto.getCodeCommune() != null) {
            coreTerritoireService.findCommune(dto.getCodeCommune()).ifPresent(entity::setCommune);
        }
        if (dto.getCodeContrat() != null) {
            coreEtablissementService.findContrat(dto.getCodeContrat()).ifPresent(entity::setContrat);
        }

        entity.addSource("api");

        return Optional.of(coreEtablissementService.saveEtablissement(entity))
                .map(webEtablissementMapper::toEtablissementDto);
    }

    @Override
    public Optional<EtablissementDto> updateEtablissement(@NonNull EtablissementRequestDto dto) {
        return coreEtablissementService.findEtablissement(dto.getUai()).map(entity -> {

            if (dto.getNom() != null) entity.setNom(dto.getNom());
            if (dto.getSiret() != null) entity.setSiret(dto.getSiret());
            if (dto.getAdresse() != null) entity.setAdresse(dto.getAdresse());
            if (dto.getComplement() != null) entity.setComplement(dto.getComplement());
            if (dto.getCodePostal() != null) entity.setCodePostal(dto.getCodePostal());
            if (dto.getDateOuverture() != null) entity.setDateOuverture(dto.getDateOuverture());
            if (dto.getDateFermeture() != null) entity.setDateFermeture(dto.getDateFermeture());
            if (dto.getActif() != null) entity.setActif(dto.getActif());
            if (dto.getCodeSecteur() != null) entity.setSecteur(Secteur.valueOf(dto.getCodeSecteur()));

            if (dto.getCodeNature() != null) {
                coreEtablissementService.findNature(dto.getCodeNature()).ifPresent(entity::setNature);
            }
            if (dto.getCodeCommune() != null) {
                coreTerritoireService.findCommune(dto.getCodeCommune()).ifPresent(entity::setCommune);
            }
            if (dto.getCodeContrat() != null) {
                coreEtablissementService.findContrat(dto.getCodeContrat()).ifPresent(entity::setContrat);
            }

            entity.addSource("api");

            return webEtablissementMapper.toEtablissementDto(coreEtablissementService.saveEtablissement(entity));
        });
    }

    @Override
    @Transactional(readOnly = true)
    public EtablissementDetailsDto findEtablissementDetailsDtoByUai(@NonNull String uai) {

        Optional<EtablissementEntity> entity = coreEtablissementService.findEtablissementByUai(uai);

        if (entity.isEmpty()) {
            return null;
        }

        var metadataList = coreEtablissementService.getMetadataListByUai(uai);

        // IPS
        List<IndicesPositionSocialeDTO> ips = metadataList.stream()
                .filter(m -> m.getMetadatas().getIps() != null)
                .map(webEtablissementMapper::toIndicesPositionSocialeDTO)
                .toList();

        // IVA
        List<IndicateurValeurAjouteeDTO> iva = metadataList.stream()
                .filter(m -> m.getMetadatas().getIva() != null)
                .map(webEtablissementMapper::toIndicateurValeurAjouteeDTO)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(IndicateurValeurAjouteeDTO::getAnnee).reversed()
                        .thenComparing(IndicateurValeurAjouteeDTO::getFiliere))
                .toList();

        return new EtablissementDetailsDto(
                entity.map(webEtablissementMapper::toEtablissementDto).orElse(null),
                webEtablissementMapper.toOptionWithCategorieDto(coreEtablissementService.getOptionListByUai(uai)),
                coreEtablissementService.getSpecialiteListByUai(uai).stream().map(webEtablissementMapper::toSpecialiteBac).toList(),
                webEtablissementMapper.toLangueWithCategorieDtoList(coreEtablissementService.getLangueListByUai(uai)),
                webEtablissementMapper.toSportWithCategorieDtoList(coreEtablissementService.getSportListByUai(uai)),
                coreEtablissementService.getContactListByUai(uai).stream().map(webEtablissementMapper::toContactDto).toList(),
                coreEtablissementService.getJourneesPortesOuvertes(uai).stream().map(webEtablissementMapper::toJPODto).toList(),
                ips,
                iva,
                coreFormationService.findFormations(uai).stream().map(webEtablissementMapper::toFormationDto).distinct().toList());
    }

    @Override
    public List<NatureDto> getNatureList() {
        return coreEtablissementService.getNatureList().stream().map(webEtablissementMapper::toNatureDto).toList();
    }
}
