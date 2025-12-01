package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.CertificationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.RomeEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.shell.datasets.CODESROME;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class ShellFormationServiceImpl implements ShellFormationService {

    private final FormationMapper formationMapper;
    private final CoreFormationService coreFormationService;
    private final CoreEtablissementService coreEtablissementService;
    private final ShellEntityService shellEntityService;

    @Autowired
    public ShellFormationServiceImpl(FormationMapper formationMapper, CoreFormationService coreFormationService, CoreEtablissementService coreEtablissementService, ShellEntityService shellEntityService) {
        this.formationMapper = formationMapper;
        this.coreFormationService = coreFormationService;
        this.coreEtablissementService = coreEtablissementService;
        this.shellEntityService = shellEntityService;
    }

    @Override
    public String createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets) {

        datasets.forEach(dataset -> {
            log.info(dataset.toString());
        });

        return String.format("Import terminé : %d formations(s) CPF enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateFormationsOnisepEsr(@NonNull List<OnisepFormationDataset> datasets) {

        datasets.stream()
                .collect(groupingBy(OnisepFormationDataset::getFormationId))
                .forEach((id, datasetList) -> {
                    OnisepFormationDataset dataset = datasetList.getFirst();
                    Optional<FormationEntity> formationEntityOptional = coreFormationService.findFormationByOnisepId(dataset.getFormationOnisepId());

//                    EtablissementEntity etablissementEntity = null;

//                    Optional<EtablissementEntity> etablissementEntityOptional = coreEtablissementService.findEtablissement(dataset.getEtablissementUai());
//
//                    if (etablissementEntityOptional.isPresent()) {
//                        etablissementEntity = etablissementEntityOptional.get();
//                    }

                    FormationEntity formationEntity = null;
                    formationEntity = formationEntityOptional.orElseGet(() -> formationMapper.toFormationEntity(dataset));
//                    formationEntity.setEtablissement(etablissementEntity);

                    coreFormationService.saveFormation(formationEntity);

                    if (dataset.getFormationOnisepId().equals(1112)) {

                        ActionFormationEntity actionFormationEntity = null;

                        datasets.forEach(ds -> {
                            log.info("{}/{}/{}", ds.getEtablissementUai(), ds.getEtablissementNom(), ds.getActionFormationOnisepId());
                        });
                    }
                });

        return String.format("Import terminé : %d formationss Onisep enregistré(s.", datasets.size());
    }

    @Deprecated
    public String createOrUpdateFormationsOnisepIdf() {

        try {
            JAXBContext context = JAXBContext.newInstance(LheoSubtype.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<LheoSubtype> jaxbElement = unmarshaller.unmarshal(new StreamSource(new File("datasets/lheo_action_IDF.xml")), LheoSubtype.class);
            LheoSubtype lheoSubtype = jaxbElement.getValue();

            lheoSubtype.getOffres().getFormation().forEach(formation -> {

                if (formation.getNumero().startsWith("FOR.389")) {
                    log.info("{} {} {}", formation.getNumero(), formation.getIntituleFormation().getValue(), formation.getContactFormation().getFirst().getCoordonnees().getAdresse().getDenomination().getValue());
                }
            });

            return String.format("Import terminé : %d formations(s) ONISEP enregistrée(s).", lheoSubtype.getOffres().getFormation().size());
        } catch (JAXBException e) {
            return String.format(e.getMessage(), e.getCause().getMessage());
        }

    }

    public String createOrUpdateFormations(@NonNull List<CPFFormationDataset> datasets) {

        Map<UUID, List<CPFFormationDataset>> group = datasets.stream().collect(groupingBy(CPFFormationDataset::getId));

        group.forEach((id, datasetList) -> {
            Optional<FormationEntity> formationEntityOptional = coreFormationService.findFormation(id);

            if (formationEntityOptional.isPresent()) {

            } else {
                FormationEntity entity = formationMapper.toFormationEntity(datasetList.getFirst());
                coreFormationService.saveFormation(entity);
            }
        });


        return String.format("Import terminé : %d formations traitées.", datasets.size());
    }

    public String createOrUpdateFormationsOnisep(@NonNull List<OnisepFormationDataset> datasets) {

        datasets.stream().collect(groupingBy(OnisepFormationDataset::getFormationOnisepId)).forEach((onisepId, datasetList) -> {
            FormationEntity formation = shellEntityService.findFormationByOnisepId(datasetList.getFirst());
        });

        return "OK";
    }

    @Override
    public String createOrUpdateCertificationsRncp(@NonNull FICHES fiches) {

        log.info("Début traitement romes");
        coreFormationService.saveRomes(fiches.getFICHE()
                .stream()
                .map(FICHES.FICHE::getCODESROME)
                .flatMap(codesRome -> codesRome == null ? Stream.empty() : Stream.of(codesRome))
                .map(codesRome -> codesRome == null ? null : codesRome.getROME())
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(formationMapper::toRomeEntity)
                .toList());
        log.info("Fin traitement romes");

        fiches.getFICHE().forEach(fiche -> {

            var certificationCode = fiche.getNUMEROFICHE();

            Set<RomeEntity> romes = coreFormationService.getRomes(
                    Stream.of(fiche)
                            .map(FICHES.FICHE::getCODESROME)
                            .flatMap(codesRome -> codesRome == null ? Stream.empty() : Stream.of(codesRome))
                            .map(codesRome -> codesRome == null ? null : codesRome.getROME())
                            .filter(Objects::nonNull)
                            .flatMap(List::stream)
                            .map(CODESROME.ROME::getCODE)
                            .toList());

//            log.info("Taille : {}", romes.size());

            if (certificationCode.startsWith("RNCP")) {
                Optional<CertificationEntity> certificationEntityOptional = coreFormationService.findCertificationByRNCP(certificationCode);
                if (certificationEntityOptional.isEmpty()) {
                    CertificationEntity entity = new CertificationEntity();
                    entity.setId(UUID.nameUUIDFromBytes(certificationCode.getBytes()));
                    entity.setCodeRNCP(certificationCode);
                    if (fiche.getINTITULE().length() > 255)
                        entity.setNom(fiche.getINTITULE().substring(0, 255));
                    else
                        entity.setNom(fiche.getINTITULE());
                    entity.setRomes(romes);

                    coreFormationService.saveCertification(entity);
                } else {
                    CertificationEntity entity = certificationEntityOptional.get();
                    entity.setRomes(romes);
                    coreFormationService.saveCertification(entity);
                }
            }
        });

        return String.format("Import terminé : %d certifications traitées.", fiches.getFICHE().size());
    }

    @Override
    public String createOrUpdateFormationsCarif(@NonNull List<CarifFormationDataset> datasets) {
        return String.format("Import terminé : %d formations carif traitées.", datasets.size());
    }

    @Override
    public void createOrUpdateFormationsParcoursup(@NonNull List<ParcoursupFormationDataset> datasets) {

        Map<Integer, List<ParcoursupFormationDataset>> group = datasets
                .stream().filter(d -> d.getCodeFormation() != null)
                .collect(groupingBy(ParcoursupFormationDataset::getCodeFormation));


        group.forEach((parcoursupId, actions) -> {
            // 1. Recherche de la formation
            FormationEntity entity;
            Optional<FormationEntity> formationEntityOptional = coreFormationService.findFormationByParcoursupId(parcoursupId);

            if (formationEntityOptional.isPresent()) {
                // Update ?
                entity = formationEntityOptional.get();
            } else {
                // création
                entity = new FormationEntity();
                entity.setId(UUID.nameUUIDFromBytes(parcoursupId.toString().getBytes()));
                entity.setNom(actions.getFirst().getNomFormation()); // TODO à vérifier
                //objectif
                //resultats
                //contenu
                //certifiante
                //parcoursDeFormation (1)
                //codeNiveauEntree
                //certifications
                //codeNiveauSortie
                //actions (voir plus bas)
                //etablissement (voir plus bas)
                //organisme (voir plus bas)
                //identifiantModule
                //positionnement
                //onisepId
                entity.setParcoursupId(parcoursupId);
                coreFormationService.saveFormation(entity);
            }

            // 2. Les actions de formation
            log.info("Nombre d'actions de formations : {}", actions.size());

            List<ActionFormationEntity> actionFormationEntityList = actions
                    .stream()
                    .map(a -> shellEntityService.toActionFormationEntity(a, entity))
                    .toList();
        });

//        Map<String, List<ParcoursupFormationDataset>> group = datasets.stream().collect(groupingBy(ParcoursupFormationDataset::getCodeInterneFormation));

//        var l = datasets.stream()
//                .filter(dataset -> dataset.getAnnee().equals("2025"))
//                .map(formationMapper::toFormationEntity)
//                .toList();

//        log.info("{} formations Parcoursup sélectionnées.", l.size());

        log.info("Import terminé : {} formations Parcoursup traitées.", datasets.size());
    }

    @Override
    public String createOrUpdateFormationsOnisepLheo(@NonNull LheoSubtype lheoSubtype) {
        return "";
    }
}
