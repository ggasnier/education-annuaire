package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.services.CoreElasticService;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShellEtablissementServiceImpl implements ShellEtablissementService {

    private final EtablissementMapper etablissementMapper;
    private final CoreEtablissementService coreEtablissementService;
    private final CoreElasticService coreElasticService;
    private final ShellEntityService shellEntityService;
    private final ValidatorService validatorService;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    int chunk;

    @Override
    public void createOrUpdateOrganismes(@NonNull List<TravailOrganismeFormationDataset> datasets) {

        for (int i = 0; i < datasets.size(); i += chunk) {
            List<? extends TravailOrganismeFormationDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveOrganismes(sub.stream()
                    .map(shellEntityService::toOrganismeEntity)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }

        log.info("Import terminé : {} organismes(s) traité(s).", datasets.size());
    }

    @Override
    public void createOrUpdateSports(@NonNull List<SportDataset> datasets, String categorie) {
        coreEtablissementService.saveEtablissementSportEntity(datasets
                .stream()
                .map(dataset -> shellEntityService.toEtablissementSportEntity(dataset, categorie))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} sports enregistré(s).", datasets.size());
    }

    @Override
    public void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets) {

        // Enregistrement comme une option
        coreEtablissementService.saveOptions(datasets.stream()
                .filter(d -> d.getOption() != null)
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(shellEntityService::toOptionEtablissementEntity)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        // Le sport (section sportive et sport études)

        log.info("Import des dispositifs sections européennes");
        // Les sections européennes
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_EUROPEENNE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, "EU"))
                .flatMap(List::stream)
                .toList());

        log.info("Import des dispositifs sections langues orientales");
        // Langues orientales
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_ORIENTALE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, "LO"))
                .flatMap(List::stream)
                .toList());

        log.info("Import des dispositifs sections internationales");
        // Sections internationales
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, "SI"))
                .flatMap(List::stream)
                .toList());

        log.info("Import des dispositifs sections bilingues");
        // Sections internationales
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_BILINGUE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, "BI"))
                .flatMap(List::stream)
                .toList());

        log.info("Import terminé : {} dispositifs enregistré(s).", datasets.size());
    }

    @Override
    public <T extends IndicePositionSociale & Metadata> void createOrUpdateIPS(@NonNull List<T> datasets) {
        coreEtablissementService.saveMetadata(datasets.stream()
                .map(shellEntityService::toEtablissementMetadataEntity)
                .filter(Objects::nonNull)
                .toList());
    }

    @Override
    public <T extends Effectifs & Metadata> void createOrUpdateEffectifs(List<T> datasets) {
        coreEtablissementService.saveMetadata(
                datasets.stream()
                        .map(shellEntityService::toEtablissementMetadataEntity)
                        .filter(Objects::nonNull)
                        .toList());
    }

    @Override
    public <T extends IndicateurValeurAjoutee & Metadata> void createOrUpdateIVA(List<T> datasets) {
        coreEtablissementService.saveMetadata(datasets.stream()
                .map(shellEntityService::toEtablissementMetadataEntity)
                .filter(Objects::nonNull)
                .toList());
    }

    @Override
    public void createOrUpdateEuroscol(List<EuroscolDataset> datasets) {

        coreEtablissementService.saveOptions(datasets.stream()
                .map(shellEntityService::toOptionEtablissementEntity)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source) {

        long startTime = System.nanoTime();

        // Les établissements
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<? extends EtablissementDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveEtablissements(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(dataset -> shellEntityService.toEtablissementEntity(dataset, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les options
            coreEtablissementService.saveOptions(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(shellEntityService::toOptionEtablissementEntity)
                    .flatMap(List::stream)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les contacts
        }

        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime) / 1_000_000_000.00;
        double vitesse = datasets.size() / totalTime;
        var decimalFormat = new DecimalFormat("#.00");

        log.info("Vitesse de traitement : {} etabs/s", decimalFormat.format(vitesse));

        log.info("Import terminé : {} établissements(s) traité(s).", datasets.size());
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
    public void createOrUpdateNatures(@NonNull List<NatureDataset> datasets) {
        coreEtablissementService.saveNatures(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toNatureEntity)
                .toList());
        log.info("Import terminé : {} natures(s) enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public void createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
        coreEtablissementService.saveContrats(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toContratEntity)
                .toList());
        log.info("Import terminé : {} contrat(s) enregistré(s).", datasets.size());
    }

//    @Override
//    public String createOrUpdateIPS(@NonNull List<? extends IPSDataset> datasets, String categorie) {
//        coreEtablissementService.saveIPS(datasets.stream()
//                .map(ipsDataset -> shellEntityService.toIndicePositionSocialeEntity(ipsDataset, categorie))
//                .filter(Objects::nonNull)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());
//        return String.format("Import terminé : %d ips enregistré(s).", datasets.size());
//    }

//    @Override
//    @Transactional
//    public String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets) {
//        coreEtablissementService.saveSectionsSporties(datasets
//                .stream()
//                .map(shellEntityService::toSectionSportiveEntity)
//                .flatMap(List::stream)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());
//        return String.format("Import terminé : %d sections sportives enregistrée(s).", datasets.size());
//    }
//
//    @Override
//    public String createOrUpdateSectionsSportEtudes(@NonNull List<SectionSportEtudeDataset> datasets) {
//        coreEtablissementService.saveSectionsSportEtudes(datasets
//                .stream()
//                .map(shellEntityService::toSportEtudeEntity)
//                .filter(Objects::nonNull)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());
//        return String.format("Import terminé : %d sections sport etudes enregistrée(s).", datasets.size());
//    }

    @Override
    @Transactional
    public void createOrUpdateLangues(@NonNull List<LangueDataset> datasets) {
        coreEtablissementService.saveLangues(datasets
                .stream()
                .map(shellEntityService::toLangueEntity)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} langues enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public void createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets) {
        coreEtablissementService.saveSpecialites(datasets
                .stream()
                .map(shellEntityService::toSpecialiteEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} specialités enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public void createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets) {
        // Indicateurs Section Internationnale et BFI
        coreEtablissementService.saveOptions(datasets.stream()
                .map(shellEntityService::toOptionEtablissementEntity)
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        // Details langues


        log.info("Import terminé : {} sections internationale(s).", datasets.size());
    }

    @Override
    @Transactional
    public void createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets) {
        coreEtablissementService.saveOptions(datasets
                .stream()
                .map(shellEntityService::toOptionEtablissementEntity)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} sections binationale enregistrée(s).", datasets.size());
    }

}
