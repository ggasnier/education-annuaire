package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.referentiels.MetierAppellationDto;
import com.guillaumegasnier.education.core.domains.referentiels.MetierBlocDto;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierSavoirEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import com.guillaumegasnier.education.shell.transformers.ReferentielTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShellReferencielServiceImpl implements ShellReferencielService {

    private final CoreEtablissementService coreEtablissementService;
    private final CoreReferentielService coreReferentielService;
    private final CoreRechercheService coreRechercheService;
    private final EtablissementMapper etablissementMapper;
    private final ReferentielMapper referentielMapper;
    private final ReferentielTransformer referentielTransformer;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:500}")
    int chunk = 500;

    @Override
    public void createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
        coreEtablissementService.saveContrats(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toContratEntity)
                .toList());
        log.info("Import terminé : {} contrats traités", datasets.size());
    }

    @Override
    public void createOrUpdateNatures(@NonNull List<NatureDataset> datasets) {
        coreEtablissementService.saveNatures(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toNatureEntity)
                .toList());
        log.info("Import terminé : {} natures traitées", datasets.size());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createOrUpdateCertificationsRncp(@NonNull FICHES fiches) {
        log.info("Fiches : {}", fiches.getFICHE().size());

        int size = fiches.getFICHE().size();

        for (int i = 0; i < fiches.getFICHE().size(); i += chunk) {
            List<FICHES.FICHE> sub = fiches.getFICHE().subList(i, Math.min(i + chunk, size));
            coreReferentielService.saveCertifications(sub
                    .stream()
                    .map(referentielMapper::toCertificationDTO)
                    .map(referentielTransformer::toCertificationNationaleEntity)
                    .toList());
        }
    }

    @Override
    public void createOrUpdateRome(@NonNull List<RomeDataset> romes, @NonNull List<RomeAppellationDataset> appellations, @NonNull List<RomeBlocDataset> blocs) {

        List<MetierEntity> metiers = romes.stream()
                .map(referentielTransformer::toMetierEntity)
                .map(entity -> {
                    var a = entity.getMetdatas().getAppellations();
                    a.addAll(appellations
                            .stream()
                            .filter(aa -> aa.getCodeRome().equals(entity.getCode()))
                            .map(this::toMetierAppellationDto)
                            .toList());
                    entity.getMetdatas().setAppellations(a);
                    return entity;
                })
                .map(entity -> {
                    var a = entity.getMetdatas().getBlocs();
                    a.addAll(blocs
                            .stream()
                            .filter(aa -> aa.getCodeRome().equals(entity.getCode()))
                            .map(this::toMetierBlocDto)
                            .toList());
                    entity.getMetdatas().setBlocs(a);
                    return entity;
                })
                .toList();

        coreReferentielService.saveMetiers(metiers);

        // Synchronisation ES : mise à jour de l'index en même temps que PostgreSQL TODO à déplacer
        coreRechercheService.saveMetiers(
                metiers.stream().map(referentielMapper::toRechercheMetierEntity).toList());
    }

    private MetierBlocDto toMetierBlocDto(RomeBlocDataset dataset) {
        MetierBlocDto dto = new MetierBlocDto();
        dto.setCode(dataset.getCodeCompoBloc());
        dto.setPosition(dataset.getPosition());
        dto.setTexte(dataset.getTexte());
        return dto;
    }

    private MetierAppellationDto toMetierAppellationDto(RomeAppellationDataset dataset) {
        MetierAppellationDto dto = new MetierAppellationDto();
        dto.setNom(dataset.getNom());
        dto.setEmploiCadre(dataset.getEmploiCadre());
        dto.setTransitionEco(dataset.getTransitionEco());
        dto.setTransitionNum(dataset.getTransitionNum());
        dto.setTransitionDemo(dataset.getTransitionDemo());
        dto.setEmploiReglemente(dataset.getEmploiReglemente());
        return dto;
    }

    @Override
    public void createOrUpdateRome(@NonNull List<ArborescenceCompetenceDataset> datasets) {
        // 1. Domaine de compétence
        coreReferentielService.saveDomaines(
                datasets.stream()
                        .map(referentielMapper::toDomaineCompetenceDTO)
                        .distinct()
                        .map(referentielTransformer::toDomaineCompetenceEntity)
                        .toList());
        // 2. Enjeu
        coreReferentielService.saveEnjeux(
                datasets.stream()
                        .map(referentielMapper::toEnjeuDTO)
                        .distinct()
                        .map(referentielTransformer::toEnjeuEntity)
                        .toList());
        // 3. Objectif
        coreReferentielService.saveObjectifs(
                datasets.stream()
                        .map(referentielMapper::toObjectifDTO)
                        .distinct()
                        .map(referentielTransformer::toObjectifEntity)
                        .toList());
        // 4. MacroCompetence
        coreReferentielService.saveMacroCompetences(
                datasets.stream()
                        .map(referentielMapper::toMacroCompetenceDTO)
                        .distinct()
                        .map(referentielTransformer::toMacroCompetenceEntity)
                        .toList());
        // 5. Competence
        coreReferentielService.saveCompetences(
                datasets.stream()
                        .map(referentielMapper::toCompetenceDTO)
                        .distinct()
                        .filter(dto -> dto.getCode() > 0)
                        .map(referentielTransformer::toCompetenceEntity)
                        .toList());
    }

    @Override
    public void createOrUpdateRome(@NonNull List<SavoirDataset> savoirDatasetList, @NonNull List<RomeSavoirDataset> savoirRubriqueDatasetList) {
        List<MetierSavoirEntity> savoirs = savoirDatasetList.stream()
                .map(referentielTransformer::toMetierSavoirEntity)
                .toList();
        coreReferentielService.saveSavoirs(savoirs);

        coreReferentielService.saveMetierSavoirLien(savoirRubriqueDatasetList.stream()
                .filter(dataset -> dataset.getCodeRome().equals("M1805")) // todo : remove filter when all data is available
                .map(referentielTransformer::toMetierSavoirLienEntity)
                .filter(Objects::nonNull)
                .toList());

        coreReferentielService.saveMetierCompetenceLien(savoirRubriqueDatasetList.stream()
                .filter(dataset -> dataset.getCodeRome().equals("M1805")) // todo : remove filter when all data is available
                .map(referentielTransformer::toMetierCompetenceEntity)
                .filter(Objects::nonNull)
                .toList());


    }
}
