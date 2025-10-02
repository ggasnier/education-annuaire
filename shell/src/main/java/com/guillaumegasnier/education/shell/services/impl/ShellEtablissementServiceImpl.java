package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.OptionEtablissementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
public class ShellEtablissementServiceImpl implements ShellEtablissementService {

    private final EtablissementMapper etablissementMapper;
    private final CoreEtablissementService coreEtablissementService;
    private final ShellEntityService shellEntityService;
    private final ValidatorService validatorService;

    public ShellEtablissementServiceImpl(EtablissementMapper etablissementMapper, CoreEtablissementService coreEtablissementService, ShellEntityService shellEntityService, ValidatorService validatorService) {
        this.etablissementMapper = etablissementMapper;
        this.coreEtablissementService = coreEtablissementService;
        this.shellEntityService = shellEntityService;
        this.validatorService = validatorService;
    }

    @Override
    @Transactional
    public String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source) {
        // Les établissements
        List<EtablissementEntity> etablissements = datasets.stream()
                .flatMap(this::dedoublement)
                .map(dataset -> shellEntityService.toEtablissementEntity(dataset, source))
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList();
        coreEtablissementService.saveEtablissements(etablissements);

        // Les options
        List<OptionEtablissementEntity> options = datasets.stream()
                .flatMap(this::dedoublement)
                .map(shellEntityService::toOptionEtablissementEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList();
        coreEtablissementService.saveOptions(options);

        // Les contacts

        return String.format("Import terminé : %d établissements(s) enregistrée(s).", datasets.size());
    }

    /**
     * <p>Dédoublement des datasets</p>
     * <ul>
     *     <li>UAI séparés par ";"</li>
     *     <li>SIRET séparés par ","</li>
     * </ul>
     */
    public Stream<EtablissementDataset> dedoublement(@NonNull EtablissementDataset etablissementDataset) {
        return Stream.of(etablissementDataset)
                .flatMap(dataset ->
                        Arrays.stream(dataset.getUai().split(";"))
                                .map(String::trim)
                                .filter(uai -> !uai.isEmpty())
                                .map(dataset::cloneWithUai))
                .flatMap(dataset -> {
                    String siretField = dataset.getSiret();
                    if (siretField == null || siretField.isBlank()) {
                        return Stream.of(dataset);
                    }
                    return Arrays.stream(siretField.split(","))
                            .map(String::trim)
                            .filter(siret -> !siret.isEmpty())
                            .distinct()
                            .map(dataset::cloneWithSiret);
                });
    }

    @Override
    @Transactional
    public String createOrUpdateNatures(@NonNull List<NatureDataset> datasets) {
        coreEtablissementService.saveNatures(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toNatureEntity)
                .toList());
        return String.format("Import terminé : %d natures(s) enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
        coreEtablissementService.saveContrats(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toContratEntity)
                .toList());
        return String.format("Import terminé : %d contrat(s) enregistré(s).", datasets.size());
    }

    @Override
    public String createOrUpdateIPS(@NonNull List<? extends IPSDataset> datasets, String categorie) {
        coreEtablissementService.saveIPS(datasets.stream()
                .map(ipsDataset -> shellEntityService.toIndicePositionSocialeEntity(ipsDataset, categorie))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d ips enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets) {
        coreEtablissementService.saveSectionsSporties(datasets
                .stream()
                .map(shellEntityService::toSectionSportiveEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d sections sportives enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateSectionsSportEtudes(@NonNull List<SectionSportEtudeDataset> datasets) {
        coreEtablissementService.saveSectionsSportEtudes(datasets
                .stream()
                .map(shellEntityService::toSportEtudeEntity)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d sections sport etudes enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateLangues(@NonNull List<LangueDataset> datasets) {
        coreEtablissementService.saveLangues(datasets
                .stream()
                .map(shellEntityService::toLangueEntity)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d langues enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets) {
        coreEtablissementService.saveSpecialites(datasets
                .stream()
                .map(shellEntityService::toSpecialiteEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d specialités enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets) {
        coreEtablissementService.saveSectionsInternationales(datasets.stream()
                .map(shellEntityService::toSectionInternationaleEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d sections internationale(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets) {
        coreEtablissementService.saveOptions(datasets
                .stream()
                .map(shellEntityService::toOptionEtablissementEntity)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d sections binationale enregistrée(s).", datasets.size());
    }

}
