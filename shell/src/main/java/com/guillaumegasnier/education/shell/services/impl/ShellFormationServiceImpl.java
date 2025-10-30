package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.formations.CertificationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.RomeEntity;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.shell.datasets.CODESROME;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class ShellFormationServiceImpl implements ShellFormationService {

    private final FormationMapper formationMapper;
    private final CoreFormationService coreFormationService;
    private final ShellEntityService shellEntityService;

    @Autowired
    public ShellFormationServiceImpl(FormationMapper formationMapper, CoreFormationService coreFormationService, ShellEntityService shellEntityService) {
        this.formationMapper = formationMapper;
        this.coreFormationService = coreFormationService;
        this.shellEntityService = shellEntityService;
    }

    @Override
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

    @Override
    public String createOrUpdateFormationsOnisep(@NonNull List<OnisepFormationDataset> datasets) {

//        datasets.stream().collect(groupingBy(OnisepFormationDataset::getFormationNiveauSortie)).forEach((formationNiveauSortie, datasetList) -> {
//            log.info("Formation niveau sortie : {} ({})", formationNiveauSortie, datasetList.size());
//        });

        datasets.stream().collect(groupingBy(OnisepFormationDataset::getFormationOnisepId)).forEach((onisepId, datasetList) -> {
            FormationEntity formation = shellEntityService.findFormationByOnisepId(datasetList.getFirst());

//            log.info("Formation : {} {} {} ({})", onisepId, datasetList.getFirst().getFormationNom(), datasetList.getFirst().getCodeCertification(), datasetList.size());

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
}
