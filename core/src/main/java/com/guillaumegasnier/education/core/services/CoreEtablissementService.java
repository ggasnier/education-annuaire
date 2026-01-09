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

//    void saveIPS(@NonNull List<IndicePositionSocialeEntity> entities);

    void saveSpecialites(List<EtablissementSpecialiteEntity> entities);

    void saveLangues(List<EtablissementLangueEntity> entities);

    void saveOptions(List<EtablissementOptionEntity> entities);

    /**
     * Recherche d'un établissment par son UAI
     *
     * @param uai Unité administrative immatriculée
     * @return Etablissment ou organisme de formation
     */
    Optional<EtablissementEntity> findEtablissement(@ValidUai String uai);

    boolean isEtablissementExiste(@ValidUai String uai);

    EtablissementEntity getEtablissementReferenceByUai(@ValidUai String uai);

    Optional<OrganismeEntity> findOrganisme(String nda);

    Optional<NatureEntity> findNature(String codeNature);

    Optional<ContratEntity> findContrat(String codeContrat);

//    Optional<IndicePositionSocialeEntity> findIPS(String uai, int annee);

    List<EtablissementOptionEntity> getOptionListByUai(String uai);

    List<EtablissementLangueEntity> getLangueListByUai(String uai);

    List<EtablissementSportEntity> getSportListByUai(String uai);

    //void saveSectionsSportEtudes(@NonNull List<SportEtudeEntity> entities);

    //List<SectionSportiveEntity> getSectionSportiveListByUai(String uai);

//    List<IndicePositionSocialeEntity> getIPSListByUai(String uai);

    EtablissementEntity saveEtablissement(EtablissementEntity entity);

    List<EtablissementEntity> findAll();

//    List<EtablissementEntity> findEtablissementByNda(String numeroDeclarationActivite);

    List<EtablissementEntity> findEtablissementListByDepartement(String code);

    List<EtablissementSpecialiteEntity> getSpecialiteListByUai(String uai);

    List<EtablissementEntity> findEtablissementListByCommune(String code);

    void saveOrganisme(OrganismeEntity entity);

    void saveOrganismes(List<OrganismeEntity> entities);

    void saveEtablissementSportEntity(List<EtablissementSportEntity> entities);

    int getNbrEtablissements();

    Optional<EtablissementMetadataEntity> findMetadata(String uai, Integer annee);

    void saveMetadata(List<EtablissementMetadataEntity> entities);

    List<EtablissementMetadataEntity> getMetadataListByUai(String uai);

    void saveContacts(@NonNull List<EtablissementContactEntity> entities);

    List<EtablissementContactEntity> getContactListByUai(String uai);

    void saveJPO(List<EtablissementJPOEntity> entities);

    List<EtablissementJPOEntity> getJourneesPortesOuvertes(String uai);
}
