package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.validations.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.dto.etablissements.OptionDTO;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import com.guillaumegasnier.education.shell.transformers.EtablissementTransformer;
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
    private final EtablissementTransformer etablissementTransformer;
    private final EtablissementMapper etablissementMapper;
    private final CoreRechercheService coreRechercheService;
    private final ValidatorService validatorService;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:500}")
    int chunk = 500;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateSports(@NonNull List<SportDataset> datasets, Sport.Categorie categorie, @NonNull String source) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<SportDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveEtablissementSportEntity(sub.stream()
                    .map(dataset -> etablissementMapper.toSportDTO(dataset, categorie))
                    .flatMap(List::stream)
                    .map(dto -> etablissementTransformer.toEtablissementSportEntity(dto, source))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
        log.info("Import terminé : {} sports enregistré(s).", datasets.size());
    }

//    @Override
//    public void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets, String source) {
//
//        log.info("Import des dispositifs comme option");
//        coreEtablissementService.saveOptions(datasets.stream()
//                .filter(d -> d.getOption() != null)
//                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
//                .map(etablissementTransformer::toEtablissementOptionEntity)
//                .filter(Objects::nonNull)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());
//
//        // Le sport (section sportive et sport études)
//
//        log.info("Import des dispositifs sections européennes");
//        coreEtablissementService.saveLangues(datasets.stream()
//                .filter(d -> d.getOption() != null
//                        && d.getOption().equals(OptionEtablissement.SECTION_EUROPEENNE))
//                .filter(d -> d.getUai() != null && !d.getUai().isBlank())
//                .map(l -> etablissementTransformer.toLangueEntity(l, Langue.Categorie.EU))
//                .flatMap(List::stream)
//                .map(validatorService::toValidEntity)
//                .filter(Objects::nonNull)
//                .toList());
//    }

//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets, @NonNull String source) {
//        int size = datasets.size();
//        for (int i = 0; i < size; i += chunk) {
//            List<OnisepDispositifDataset> sub = datasets.subList(i, Math.min(i + chunk, size));
//            log.info("Options: {}/{}", i, size);
//        }
//    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets, @NonNull String source) {
        int size = datasets.size();
        for (int i = 0; i < size; i += chunk) {
            List<OnisepDispositifDataset> sub = datasets.subList(i, Math.min(i + chunk, size));

            log.info("Import des dispositifs: {}/{}", i, size);
            coreEtablissementService.saveOptions(sub.stream()
                    .filter(d -> d.getOption() != null)
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(etablissementMapper::toOptionDTO)
                    .map(dto -> etablissementTransformer.toEtablissementOptionEntity(dto, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            coreEtablissementService.saveEtablissementSportEntity(sub.stream()
                    .filter(d -> d.getOption() != null &&
                            (d.getOption().equals(OptionEtablissement.SPORT_ETUDES) ||
                                    d.getOption().equals(OptionEtablissement.SECTION_SPORT)))
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(dataset -> etablissementMapper.toSportDTO(dataset, OptionEtablissement.SECTION_SPORT))
                    .flatMap(List::stream)
                    .map(dto -> etablissementTransformer.toEtablissementSportEntity(dto, source))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            coreEtablissementService.saveLangues(sub.stream()
                    .filter(d -> d.getOption() != null
                            && d.getOption().equals(OptionEtablissement.SECTION_ORIENTALE))
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(etablissementMapper::toLangueDTO)
                    .flatMap(List::stream)
                    .map(l -> etablissementTransformer.toEtablissementLangueEntity(l, "onisep"))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            coreEtablissementService.saveLangues(sub.stream()
                    .filter(d -> d.getOption() != null &&
                            (d.getOption().equals(OptionEtablissement.SECTION_EUROPEENNE) ||
                                    d.getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE) ||
                                    d.getOption().equals(OptionEtablissement.SECTION_BILINGUE) ||
                                    d.getOption().equals(OptionEtablissement.SECTION_ORIENTALE))
                    )
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(etablissementMapper::toLangueDTO)
                    .flatMap(List::stream)
                    .map(dto -> etablissementTransformer.toEtablissementLangueEntity(dto, source))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());


            coreEtablissementService.saveLangues(sub.stream()
                    .filter(d -> d.getOption() != null
                            && d.getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE))
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(etablissementMapper::toLangueDTO)
                    .flatMap(List::stream)
                    .map(l -> etablissementTransformer.toEtablissementLangueEntity(l, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            coreEtablissementService.saveLangues(sub.stream()
                    .filter(d -> d.getOption() != null
                            && d.getOption().equals(OptionEtablissement.SECTION_BILINGUE))
                    .filter(d -> d.getUai() != null && !d.getUai().isBlank())
                    .map(etablissementMapper::toLangueDTO)
                    .flatMap(List::stream)
                    .map(l -> etablissementTransformer.toEtablissementLangueEntity(l, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }

        log.info("Import terminé : {} dispositifs enregistré(s).", datasets.size());
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public <T extends IndicePositionSociale & Metadata> void createOrUpdateIPS(@NonNull List<T> datasets) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<T> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveMetadata(sub.stream()
                    .map(etablissementTransformer::toEtablissementMetadataEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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
                            .map(etablissementTransformer::toEtablissementMetadataEntity)
                            .filter(Objects::nonNull)
                            .toList());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateIVA(@NonNull List<IndicateurValeurAjouteeCollege> datasets) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<IndicateurValeurAjouteeCollege> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));
            coreEtablissementService.saveMetadata(sub.stream()
                    .map(etablissementTransformer::toEtablissementMetadataEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateIVALycees(@NonNull List<IndicateurValeurAjouteeLycee> datasets) {

        record Clef(String uai, Integer annee) {
        }

        // Fusion des deux sources (GT + Pro) sur la clef (uai, annee) :
        // on cumule les ResultatFiliereDto de chaque source dans un même set
        Map<Clef, IndicateurValeurAjouteeLycee> merged = new java.util.LinkedHashMap<>();
        for (IndicateurValeurAjouteeLycee dataset : datasets) {
            Clef clef = new Clef(dataset.getUai(), dataset.getAnnee());
            merged.merge(clef, dataset, (existing, incoming) -> {
                existing.getResultats().addAll(incoming.getResultats());
                return existing;
            });
        }

        List<IndicateurValeurAjouteeLycee> aggregated = new java.util.ArrayList<>(merged.values());

        for (int i = 0; i < aggregated.size(); i += chunk) {
            List<IndicateurValeurAjouteeLycee> sub = aggregated.subList(i, Math.min(i + chunk, aggregated.size()));
            coreEtablissementService.saveMetadata(sub.stream()
                    .map(etablissementTransformer::toEtablissementMetadataEntity)
                    .filter(Objects::nonNull)
                    .toList());
        }
    }

    @Override
    public void createOrUpdateEuroscol(@NonNull List<EuroscolDataset> datasets) {

    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateJpo(@NonNull List<MasaJpoDataset> datasets, @NonNull String source) {
        for (int i = 0; i < datasets.size(); i += chunk) {
            List<MasaJpoDataset> sub = datasets.subList(i, Math.min(i + chunk, datasets.size()));

            coreEtablissementService.saveJPO(
                    sub.stream()
                            .map(etablissementMapper::toJPODTO)
                            .map(etablissementTransformer::findUai)
                            .filter(Objects::nonNull)
                            .map(dto -> etablissementTransformer.toEtablissementJPOEntity(dto, source))
                            .filter(Objects::nonNull)
                            .map(validatorService::toValidEntity)
                            .filter(Objects::nonNull)
                            .toList());
        }

        log.info("Import terminé : {} Journées Portes Ouvertes traitées.", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateEuroscol(@NonNull List<EuroscolDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveOptions(datasets.stream()
                .map(dataset -> new OptionDTO(dataset.getUai(), OptionEtablissement.EUROSCOL, dataset.getNomEtablissement()))
                .map(dto -> etablissementTransformer.toEtablissementOptionEntity(dto, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, @NonNull String source) {

        long startTime = System.nanoTime();
        int size = datasets.size();

        for (int i = 0; i < datasets.size(); i += chunk) {
            List<? extends EtablissementDataset> sub = datasets.subList(i, Math.min(i + chunk, size));
            // Les établissements
            coreEtablissementService.saveEtablissements(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(dataset -> etablissementTransformer.toEtablissementEntity(dataset, source))
                    .filter(Objects::nonNull)
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les options
            coreEtablissementService.saveOptions(sub.stream()
                    .flatMap(this::dedoublement)
                    .map(etablissementMapper::toOptionDTO)
                    .flatMap(List::stream)
                    .map(dto -> etablissementTransformer.toEtablissementOptionEntity(dto, source))
                    .map(validatorService::toValidEntity)
                    .filter(Objects::nonNull)
                    .toList());

            // Les contacts
            coreEtablissementService.saveContacts(
                    sub.stream()
                            .flatMap(this::dedoublement)
                            .map(etablissementMapper::toContactDTO)
                            .flatMap(List::stream)
                            .map(dto -> etablissementTransformer.toEtablissementContactEntity(dto, source))
                            .filter(Objects::nonNull)
                            .map(validatorService::toValidEntity)
                            .filter(Objects::nonNull)
                            .toList()
            );

            // Les journees portes ouvertes
            coreEtablissementService.saveJPO(
                    sub.stream()
                            .flatMap(this::dedoublement)
                            .map(etablissementMapper::toJPODTO)
                            .flatMap(List::stream)
                            .filter(Objects::nonNull)
                            .map(dto -> etablissementTransformer.toEtablissementJPOEntity(dto, source))
                            .filter(Objects::nonNull)
                            .map(validatorService::toValidEntity)
                            .filter(Objects::nonNull)
                            .toList());

            if (source.equals("esr")) {
                coreEtablissementService.saveMetadata(sub.stream()
                        .flatMap(this::dedoublement)
                        .map(EtablissementDataset::getEffectifs)
                        .flatMap(List::stream)
                        .map(etablissementTransformer::toEtablissementMetadataEntity)
                        .filter(Objects::nonNull)
                        .toList());
            }

            // Les identifiants externes pour masa seulement
            if (source.equals("masa")) {
                coreEtablissementService.saveMasa(sub.stream()
                        .filter(dataset -> dataset.getUai() != null && !dataset.getUai().isBlank())
                        .map(etablissementMapper::toMasaDTO)
                        .map(etablissementTransformer::toEtablissementMasaEntity)
                        .filter(Objects::nonNull)
                        .map(validatorService::toValidEntity)
                        .filter(Objects::nonNull)
                        .toList());
            }
        }

        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime) / 1_000_000_000.00;
        double vitesse = datasets.size() / totalTime;
        var decimalFormat = new DecimalFormat("#.00");

        log.info("Vitesse de traitement : {} etabs/s", decimalFormat.format(vitesse));

        log.info("Import terminé : {} établissements(s) traité(s).", datasets.size());
    }

    /**
     * <p>
     * Dédoublement des datasets
     * </p>
     * <ul>
     * <li>UAI séparés par ";"</li>
     * <li>SIRET séparés par ","</li>
     * </ul>
     */
    public Stream<EtablissementDataset> dedoublement(@NonNull EtablissementDataset etablissementDataset) {
        return Stream.of(etablissementDataset)
                .flatMap(dataset -> Arrays.stream(dataset.getUai().split(";"))
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

    /*
     * @Override
     * public void createOrUpdateNatures(@NonNull List<NatureDataset> datasets) {
     * coreEtablissementService.saveNatures(datasets.stream()
     * .filter(dataset -> dataset.getDateFin() != null &&
     * dataset.getDateFin().isEmpty())
     * .map(etablissementMapper::toNatureEntity)
     * .toList());
     * log.info("Import terminé : {} natures(s) enregistrée(s).", datasets.size());
     * }
     */

    /*
     * @Override
     * public void createOrUpdateContrats(List<ContratDataset> datasets) {
     *
     * }
     */

    /*
     * @Override
     * public void createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
     * coreEtablissementService.saveContrats(datasets.stream()
     * .filter(dataset -> dataset.getDateFin() != null &&
     * dataset.getDateFin().isEmpty())
     * .map(etablissementMapper::toContratEntity)
     * .toList());
     * log.info("Import terminé : {} contrat(s) enregistré(s).", datasets.size());
     * }
     */

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateLangues(@NonNull List<LangueDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveLangues(datasets
                .stream()
                .map(etablissementMapper::toLangueDTO)
                .map(dto -> etablissementTransformer.toEtablissementLangueEntity(dto, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} langues enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveSpecialites(datasets
                .stream()
                .map(etablissementMapper::toSpecialiteDTO)
                .flatMap(List::stream)
                .filter(dto -> !dto.uai().isBlank())
                .map(dto -> etablissementTransformer.toEtablissementSpecialiteEntity(dto, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());
        log.info("Import terminé : {} specialités enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets, @NonNull String source) {
        // Indicateurs Section Internationale et BFI
        coreEtablissementService.saveOptions(datasets.stream()
                .map(etablissementMapper::toOptionDTO)
                .flatMap(List::stream)
                .map(dto -> etablissementTransformer.toEtablissementOptionEntity(dto, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import terminé : {} sections internationale(s).", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets, @NonNull String source) {
        coreEtablissementService.saveOptions(datasets
                .stream()
                .map(etablissementMapper::toOptionDTO)
                .map(dto -> etablissementTransformer.toEtablissementOptionEntity(dto, source))
                .filter(Objects::nonNull)
                .map(validatorService::toValidEntity)
                .filter(Objects::nonNull)
                .toList());

        log.info("Import terminé : {} sections binationale enregistrée(s).", datasets.size());
    }

//    @Override
//    public void importEtablissementsRecherche() {
//        coreRechercheService.saveEtablissements(
//                coreEtablissementService
//                        .findEtablissementsActif()
//                        .stream()
//                        .map(etablissementMapper::toRechercheEtablissementEntity)
//                        .toList());
//    }
}