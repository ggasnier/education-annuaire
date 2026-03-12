package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.dto.MetierAppellationDto;
import com.guillaumegasnier.education.core.dto.MetierBlocDto;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeAppellationDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeBlocDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.CertificationDTO;
import com.guillaumegasnier.education.shell.dto.referentiels.NSFDTO;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import com.guillaumegasnier.education.shell.transformers.ReferentielTransformer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShellReferencielServiceImpl implements ShellReferencielService {

    private final CoreEtablissementService coreEtablissementService;
    private final EtablissementMapper etablissementMapper;
    private final ReferentielMapper referentielMapper;
    private final CoreReferentielService coreReferentielService;
    private final ReferentielTransformer referentielTransformer;

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
    public void createOrUpdateCertificationsRncp(@NonNull FICHES fiches) {
        log.info("Fiches : {}", fiches.getFICHE().size());

        List<NSFDTO> nsfs = fiches.getFICHE().stream()
                .filter(fiche -> fiche.getCODESNSF() != null)
                .map(fiche -> referentielMapper.toNSFDTO(fiche.getCODESNSF().getNSF()))
                .flatMap(List::stream)
                .distinct()
                .toList();

        List<CertificationDTO> list = fiches.getFICHE().stream()
                .map(referentielMapper::toCertificationDTO)
                .toList();

        log.info("NSF : {}", nsfs.size());
    }

//    @Override
//    public void createOrUpdateRome(@NonNull List<RomeDataset> datasets) {
//        log.info("Romes : {}", datasets.size());
//
//        // Les metiers
//        coreReferentielService.saveMetiers(datasets.stream().map(referentielTransformer::toMetierEntity).toList());
//        // Les blocs
//        // Les appellations
//    }


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
}
