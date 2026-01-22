package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import com.guillaumegasnier.education.shell.transformers.EtablissementTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShellEtablissementServiceImplTest {

    @Mock
    private EtablissementMapper etablissementMapper;
    @Mock
    private CoreEtablissementService coreEtablissementService;
    @Mock
    private ValidatorService validatorService;
    @Mock
    private EtablissementTransformer etablissementTransformer;
    @InjectMocks
    private ShellEtablissementServiceImpl service;
    private String uaiExiste = "0750683K";
    private String uaiAbsent = "0750652B";
    private EtablissementEntity etablissementEntityExiste;
    private EtablissementEntity etablissementEntityAbsent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        etablissementEntityExiste = new EtablissementEntity();
        etablissementEntityExiste.setUai(uaiExiste);

        etablissementEntityAbsent = new EtablissementEntity();
        etablissementEntityAbsent.setUai(uaiAbsent);

        when(coreEtablissementService.findEtablissement(uaiExiste)).thenReturn(Optional.of(etablissementEntityExiste));
        when(coreEtablissementService.findEtablissement(uaiAbsent)).thenReturn(Optional.empty());
    }

    @Test
    void dedoublementTest() {
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste + ";" + uaiAbsent);
        dataset.setSiret("12345,3564654654");

        assertEquals(4, service.dedoublement(dataset).count());
    }


    @Test
    void createOrUpdateLanguesTest() {
        List<LangueDataset> datasets = new ArrayList<>();
        service.createOrUpdateLangues(datasets, "test");
        verify(coreEtablissementService).saveLangues(any());
    }

    @Test
    void createOrUpdateSpecialitesTest() {
        List<SpecialitePremiereDataset> datasets = new ArrayList<>();
        service.createOrUpdateSpecialites(datasets, "test");
        verify(coreEtablissementService).saveSpecialites(any());
    }

    @Test
    void createOrUpdateEuroscolTest() {
        List<EuroscolDataset> datasets = new ArrayList<>();
        service.createOrUpdateEuroscol(datasets, "test");
        verify(coreEtablissementService).saveOptions(any());
    }

    @Test
    void createOrUpdateSectionsInternationalesTest() {
        List<SectionInternationaleDataset> datasets = new ArrayList<>();
        service.createOrUpdateSectionsInternationales(datasets, "test");
        verify(coreEtablissementService).saveOptions(any());
    }

    @Test
    void createOrUpdateSectionsBinationalesTest() {
        List<SectionBinationaleDataset> datasets = new ArrayList<>();
        service.createOrUpdateSectionsBinationales(datasets, "test");
        verify(coreEtablissementService).saveOptions(any());
    }

    @Test
    void createOrUpdateSportsTest() {
        List<SportDataset> datasets = new ArrayList<>();
        var dataset = new SportDataset();
        dataset.setUai(uaiExiste);
        dataset.setNomSport("ESCRIME");
        datasets.add(dataset);
        service.createOrUpdateSports(datasets, Sport.Categorie.SE, "test");
        verify(coreEtablissementService).saveEtablissementSportEntity(any());
    }


    @Test
    void createOrUpdateDispositifsTest() {
        List<OnisepDispositifDataset> datasets = new ArrayList<>();

        OnisepDispositifDataset dataset = new OnisepDispositifDataset();
        // UAI à null
        datasets.add(dataset);
        // UAI vide
        dataset.setUai("");
        datasets.add(dataset);
        // UAI renseigné
        dataset.setUai(uaiExiste);
        datasets.add(dataset);

        dataset.setNom("");
        datasets.add(dataset);
        
        dataset.setNom("classe sport-études en collège");
        datasets.add(dataset);

        service.createOrUpdateDispositifs(datasets, "test");
        verify(coreEtablissementService).saveOptions(any());
    }

}