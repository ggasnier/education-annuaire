package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import com.guillaumegasnier.education.shell.transformers.FormationTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShellFormationServiceImpl implements ShellFormationService {

    private final FormationMapper formationMapper;
    private final CoreFormationService coreFormationService;
    private final ValidatorService validatorService;
    private final FormationTransformer formationTransformer;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    int chunk;

    @Override
    public void createOrUpdateOrganismes(@NonNull List<TravailOrganismeFormationDataset> datasets) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<? extends TravailOrganismeFormationDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreFormationService.saveOrganismes(sub.stream()
                    .map(formationTransformer::toOrganismeEntity)
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
        log.info("Import terminé : {} organismes(s) traité(s).", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateFormationsOnisepEsr(@NonNull List<OnisepFormationDataset> datasets) {
        int size = datasets.size();
        for (int i = 0; i < size; i += chunk) {
            List<? extends OnisepFormationDataset> sub = datasets.subList(i, Math.min(i + chunk, size));
            log.info("Import formations et actions {}/{}", i, size);

            // Les formations
            coreFormationService.saveFormations(sub.stream()
                    .map(formationMapper::toFormationDTO)
                    .distinct() // On ne garde que les formations
                    .map(formationTransformer::recalculId) // On recalcule l'Id
                    .map(dto -> formationTransformer.toFormationEntity(dto, "onisep"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList()
            );

            // Les actions de formations
            coreFormationService.saveActionFormation(sub.stream()
                    .map(formationMapper::toActionFormationDTO)
                    .map(dto -> formationTransformer.toActionFormationEntity(dto, "onisep"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }

        log.info("Import terminé : {} formations ONISEP ESR traitées.", size);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateFormationsCarif(@NonNull List<CarifFormationDataset> datasets) {
        int size = datasets.size();
        for (int i = 0; i < size; i += chunk) {
            List<? extends CarifFormationDataset> sub = datasets.subList(i, Math.min(i + chunk, size));
            log.info("Import formations et actions {}/{}", i, size);

            // Les formations
            coreFormationService.saveFormations(sub.stream()
                    .map(formationMapper::toFormationDTO)
                    .filter(dto -> dto.getId() != null)
                    .distinct() // On ne garde que les formations
                    .map(formationTransformer::recalculId) // On recalcule l'Id
                    .map(dto -> formationTransformer.toFormationEntity(dto, "carif"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList()

            );

            // Les actions de formations TODO
//            coreFormationService.saveActionFormation(sub.stream()
//                    .map(formationMapper::toActionFormationDTO)
//                    .map(dto -> formationTransformer.toActionFormationEntity(dto, "carif"))
//                    .toList());


//            List<FormationDTO> sub1 = sub.stream()
//                    .map(formationMapper::toFormationDTO)
//                    .toList();


        }

        log.info("Import terminé : {} formations carif traitées.", datasets.size());
    }

//    @Deprecated
//    public void createOrUpdateFormationsOnisepIdf() {
//
//        try {
//            JAXBContext context = JAXBContext.newInstance(LheoSubtype.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            JAXBElement<LheoSubtype> jaxbElement = unmarshaller.unmarshal(new StreamSource(new File("datasets/lheo_action_IDF.xml")), LheoSubtype.class);
//            LheoSubtype lheoSubtype = jaxbElement.getValue();
//
//            lheoSubtype.getOffres().getFormation().forEach(formation -> {
//
//                if (formation.getNumero().startsWith("FOR.389")) {
//                    log.info("{} {} {}", formation.getNumero(), formation.getIntituleFormation().getValue(), formation.getContactFormation().getFirst().getCoordonnees().getAdresse().getDenomination().getValue());
//                }
//            });
//
//            String.format("Import terminé : %d formations(s) ONISEP enregistrée(s).", lheoSubtype.getOffres().getFormation().size());
//        } catch (JAXBException e) {
//            String.format(e.getMessage(), e.getCause().getMessage());
//        }
//
//    }

//    public String createOrUpdateFormations(@NonNull List<CPFFormationDataset> datasets) {
//
//        Map<UUID, List<CPFFormationDataset>> group = datasets.stream().collect(groupingBy(CPFFormationDataset::getId));
//
//        group.forEach((id, datasetList) -> {
//            Optional<FormationEntity> formationEntityOptional = coreFormationService.findFormation(id);
//
//            if (formationEntityOptional.isPresent()) {
//
//            } else {
//                FormationEntity entity = formationMapper.toFormationEntity(datasetList.getFirst());
//                coreFormationService.saveFormation(entity);
//            }
//        });
//
//
//        return String.format("Import terminé : %d formations traitées.", datasets.size());
//    }

   /* @Override
    public void createOrUpdateCertificationsRncp(@NonNull FICHES fiches) {

        /*log.info("Début traitement romes");
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
        });*/

    // return String.format("Import terminé : %d certifications traitées.", fiches.getFICHE().size());
//    }

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

//            coreFormationService.saveActionFormation(actions
//                    .stream()
//                    .map(a -> shellEntityService.toActionFormationEntity(a, entity))
//                    .toList());
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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateFormationsOnisepLheo(@NonNull LheoSubtype lheoSubtype) {
        List<FormationType> datasets = lheoSubtype.getOffres().getFormation();
        var size = datasets.size();

        for (int i = 0; i < size; i += chunk) {
            List<? extends FormationType> sub = datasets.subList(i, Math.min(i + chunk, size));

//            // Les formations
//            coreFormationService.saveFormations(sub.stream()
//                    .map(formationMapper::toFormationDTO)
//                    .distinct()
//                    .map(dto -> formationTransformer.toFormationEntity(dto, "lheo"))
//                    .filter(Objects::nonNull)
//                    .map(validatorService::toValidEntity)
//                    .filter(Objects::nonNull)
//                    .toList());

            // Les actions
            coreFormationService.saveActionFormation(sub.stream()
                    .map(formationMapper::toActionFormationDTO)
                    .flatMap(List::stream)
                    .distinct()
                    .map(dto -> formationTransformer.toActionFormationEntity(dto, "lheo"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

        }

        log.info("Import terminé : {} formations LHEO traitées.", size);
    }

    @Override
    public void createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets) {

        log.info("Import terminé : {} formationss CPF traitées.", datasets.size());
    }
}
