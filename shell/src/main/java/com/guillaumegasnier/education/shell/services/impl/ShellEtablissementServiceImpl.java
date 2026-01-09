package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShellEtablissementServiceImpl implements ShellEtablissementService {

    private final CoreEtablissementService coreEtablissementService;
    private final ShellEntityService shellEntityService;
    private final ValidatorService validatorService;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    int chunk;

    @Override
    public void createOrUpdateSports(@NonNull List<SportDataset> datasets, Sport.Categorie categorie, @NonNull String source) {
        coreEtablissementService.saveEtablissementSportEntity(datasets
                .stream()
                .map(dataset -> shellEntityService.toEtablissementSportEntity(dataset, categorie, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} sports enregistré(s).", datasets.size());
    }

    @Override
    public void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets, @NonNull String source) {

//        log.info("Import des dispositifs comme option");
//        coreEtablissementService.saveOptions(datasets.stream()
//                .filter(d -> d.getOption() != null)
//                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
//                .map(o -> shellEntityService.toEtablissementOptionEntity(o, source))
//                .filter(Objects::nonNull)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());

        // Le sport (section sportive et sport études)

        log.info("Import des dispositifs sections européennes");
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_EUROPEENNE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, Langue.Categorie.EU, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import des dispositifs sections langues orientales");
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_ORIENTALE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, Langue.Categorie.LO, source))
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import des dispositifs sections internationales");
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, Langue.Categorie.SI, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import des dispositifs sections bilingues");
        coreEtablissementService.saveLangues(datasets.stream()
                .filter(d -> d.getOption() != null && d.getOption().equals(OptionEtablissement.SECTION_BILINGUE))
                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                .map(l -> shellEntityService.toLangueEntity(l, Langue.Categorie.BI, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import terminé : {} dispositifs enregistré(s).", datasets.size());
    }

    @Override
    public <T extends IndicePositionSociale & Metadata> void createOrUpdateIPS(@NonNull List<T> datasets) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<T> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveMetadata(sub.stream()
                    .map(shellEntityService::toEtablissementMetadataEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
    }

    @Override
    public <T extends Effectifs & Metadata> void createOrUpdateEffectifs(@NonNull List<T> datasets) {

        record Clef(String uai, Integer annee) {
        }


        Map<Clef, Integer> grouped = datasets.stream().
                collect(Collectors.groupingBy(
                        item -> new Clef(item.getUai(), item.getAnnee()), Collectors.summingInt(T::getEffectifs)
                ));

        List<T> aggregatedData = grouped.entrySet().stream()
                .map(entry -> {
                    T t = datasets.stream()
                            .filter(d -> d.getUai().equals(entry.getKey().uai()) && d.getAnnee().equals(entry.getKey().annee()))
                            .findFirst()
                            .orElse(null);
                    if (t != null) {
                        t.setEffectifs(entry.getValue());
                    }
                    return t;
                })
                .filter(Objects::nonNull)
                .toList();

        for (int i = 0; i < aggregatedData.size(); i += chunk) {
            List<T> sub = aggregatedData.subList(i, Math.min(i + chunk, aggregatedData.size()));
            coreEtablissementService.saveMetadata(
                    sub.stream()
                            .map(shellEntityService::toEtablissementMetadataEntity)
                            .filter(Objects::nonNull)
                            .toList());
        }
    }

    @Override
    public <T extends IndicateurValeurAjoutee & Metadata> void createOrUpdateIVA(@NonNull List<T> datasets) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<T> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveMetadata(sub.stream()
                    .map(shellEntityService::toEtablissementMetadataEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
    }

    @Override
    public void createOrUpdateEuroscol(@NonNull List<EuroscolDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveOptions(datasets.stream()
                .map(o -> shellEntityService.toEtablissementOptionEntity(o, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, @NonNull String source) {

        long startTime = System.nanoTime();

        for (int i = 0; i < datasets.size(); i += chunk) {
            List<? extends EtablissementDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            // Les établissements
            coreEtablissementService.saveEtablissements(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(dataset -> shellEntityService.toEtablissementEntity(dataset, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les options
            coreEtablissementService.saveOptions(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(dataset -> shellEntityService.toEtablissementOptionEntity(dataset, source))
                    .flatMap(List::stream)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les contacts
            coreEtablissementService.saveContacts(
                    sub.stream()
                            .flatMap(this::dedoublement)
                            .map(c -> shellEntityService.toEtablissementContactEntity(c, source))
                            .flatMap(List::stream)
                            .map(validatorService::toValidEntity)
                            .filter(Objects::nonNull)
                            .toList()
            );

            // Les journees portes ouvertes
            coreEtablissementService.saveJPO(
                    sub.stream()
                            .flatMap(this::dedoublement)
                            .map(j -> shellEntityService.toEtablissementJPOEntity(j, source))
                            .flatMap(List::stream)
                            .map(validatorService::toValidEntity)
                            .filter(Objects::nonNull)
                            .toList());


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
    public void createOrUpdateLangues(@NonNull List<LangueDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveLangues(datasets
                .stream()
                .map(l -> shellEntityService.toLangueEntity(l, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} langues enregistrée(s).", datasets.size());
    }

    @Override
    public void createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveSpecialites(datasets
                .stream()
                .map(s -> shellEntityService.toSpecialiteEntity(s, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} specialités enregistrée(s).", datasets.size());
    }

    @Override
    public void createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets, @NonNull String source) {
        // Indicateurs Section Internationnale et BFI
        coreEtablissementService.saveOptions(datasets.stream()
                .map(o -> shellEntityService.toEtablissementOptionEntity(o, source))
                .flatMap(List::stream)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        // Details langues
        // TODO

        log.info("Import terminé : {} sections internationale(s).", datasets.size());
    }

    @Override
    public void createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveOptions(datasets
                .stream()
                .map(o -> shellEntityService.toEtablissementOptionEntity(o, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        // Details langues
        // TODO

        log.info("Import terminé : {} sections binationale enregistrée(s).", datasets.size());
    }

}
