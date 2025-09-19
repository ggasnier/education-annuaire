package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.validations.ValidUai;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CoreEtablissementService {

    void saveEtablissements(@NonNull List<EtablissementEntity> entities);

    void saveNatures(@NonNull List<NatureEntity> entities);

    void saveContrats(@NonNull List<ContratEntity> entities);

    void saveIPS(@NonNull List<IndicePositionSocialeEntity> entities);

    void saveSpecialites(List<SpecialiteEntity> entities);

    void saveSectionsInternationales(@NonNull List<SectionInternationaleEntity> entities);

    void saveSectionsSporties(List<SectionSportiveEntity> entities);

    void saveLangues(List<LangueEntity> entities);

    void saveOptions(List<OptionEtablissementEntity> entities);

    Optional<EtablissementEntity> findEtablissement(@ValidUai String uai);

    Optional<NatureEntity> findNature(String codeNature);

    Optional<ContratEntity> findContrat(String codeContrat);

    Optional<IndicePositionSocialeEntity> findIPS(String uai, int annee);

    List<OptionEtablissementEntity> getOptionListByUai(String uai);

    List<LangueEntity> getLangueListByUai(String uai);

    void saveSectionsSportEtudes(@NonNull List<SportEtudeEntity> entities);

    List<SectionSportiveEntity> getSectionSportiveListByUai(String uai);

    List<IndicePositionSocialeEntity> getIPSListByUai(String uai);

    EtablissementEntity saveEtablissement(EtablissementEntity entity);
}
