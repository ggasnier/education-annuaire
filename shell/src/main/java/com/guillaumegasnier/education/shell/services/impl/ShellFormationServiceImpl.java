package com.guillaumegasnier.education.shell.services.impl;

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

import java.util.List;
import java.util.Objects;

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
    int chunk = 500;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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
                    .map(dto -> formationTransformer.toFormationEntity(dto, "onisep"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList()
            );

            // Les actions de formations
            /* coreFormationService.saveActionFormation(sub.stream()
                    .map(formationMapper::toActionFormationDTO)
                    .map(dto -> formationTransformer.toActionFormationEntity(dto, "onisep"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());*/
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
                    .filter(dto -> dto != null && dto.getId() != null)
                    .distinct() // On ne garde que les formations
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

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateFormationsParcoursup(@NonNull List<ParcoursupFormationDataset> datasets) {

        // Les formations
        coreFormationService.saveFormations(datasets.stream()
                .filter(d -> d.getAnnee() != null && d.getAnnee().equals("2026")) // Que les dernières données
                .map(formationMapper::toFormationDTO)
                .distinct() // On en garde que les formations
                .map(formationTransformer::recalculId)
                .map(dto -> formationTransformer.toFormationEntity(dto, "ps"))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import terminé : {} formations Parcoursup traitées.", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateFormationsOnisepLheo(LheoSubtype lheoSubtype) {
        if (lheoSubtype == null) {
            log.error("Données manquantes");
            return;
        }

        List<FormationType> datasets = lheoSubtype.getOffres().getFormation();
        var size = datasets.size();

        for (int i = 0; i < size; i += chunk) {
            List<? extends FormationType> sub = datasets.subList(i, Math.min(i + chunk, size));

            // Les formations
            coreFormationService.saveFormations(sub.stream()
                    .map(formationMapper::toFormationDTO)
                    .distinct()
                    .map(dto -> formationTransformer.toFormationEntity(dto, "lheo"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les actions
            /*coreFormationService.saveActionFormation(sub.stream()
                    .map(formationMapper::toActionFormationDTO)
                    .flatMap(List::stream)
                    .distinct()
                    .map(dto -> formationTransformer.toActionFormationEntity(dto, "lheo"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());*/

        }

        log.info("Import terminé : {} actions formations LHEO traitées.", size);
    }

    @Override
    public void createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets) {

        log.info("Import terminé : {} formationss CPF traitées.", datasets.size());
    }
}
