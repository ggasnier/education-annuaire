package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.CertificationDTO;
import com.guillaumegasnier.education.shell.dto.referentiels.NSFDTO;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
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
}
