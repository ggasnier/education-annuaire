package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjouteeCollege;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.etablissements.metadatas.EffectifsCollegeDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.metadatas.IPSLycee2023Dataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.metadatas.IndicateurValeurAjouteeCollegeDataset;
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

    private final String uaiExiste = "0750683K";
    private final String uaiAbsent = "0750652B";
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        EtablissementEntity etablissementEntityExiste = new EtablissementEntity();
        etablissementEntityExiste.setUai(uaiExiste);

        EtablissementEntity etablissementEntityAbsent = new EtablissementEntity();
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

    @Test
    void createOrUpdateEtablissementsTest() {
        List<EnEtablissementDataset> datasets = new ArrayList<>();
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);
        dataset.setSiret("12345,3564654654");
        datasets.add(dataset);

        dataset.setUai("");
        datasets.add(dataset);

        service.createOrUpdateEtablissements(datasets, "en");
        verify(coreEtablissementService).saveEtablissements(any());

        service.createOrUpdateEtablissements(datasets, "esr");
        verify(coreEtablissementService).saveMetadata(any());

        service.createOrUpdateEtablissements(datasets, "masa");
        verify(coreEtablissementService).saveMasa(any());

    }

    @Test
    void createOrUpdateIPSTest() {
        List<IPSLycee2023Dataset> datasets = new ArrayList<>();
        IPSLycee2023Dataset dataset = new IPSLycee2023Dataset();
        dataset.setUai(uaiExiste);
        datasets.add(dataset);
        service.createOrUpdateIPS(datasets);
        verify(coreEtablissementService).saveMetadata(any());
    }

    @Test
    void createOrUpdateEffectifsTest() {
        List<EffectifsCollegeDataset> datasets = new ArrayList<>();
        EffectifsCollegeDataset dataset = new EffectifsCollegeDataset();
        dataset.setUai(uaiExiste);
        dataset.setRentreeScolaire("2024-2025");
        dataset.setEffectifs(666);
        datasets.add(dataset);

        service.createOrUpdateEffectifs(datasets);
        verify(coreEtablissementService).saveMetadata(any());
    }

    @Test
    void createOrUpdateIVATest() {
        List<IndicateurValeurAjouteeCollege> datasets = new ArrayList<>();
        IndicateurValeurAjouteeCollegeDataset dataset = new IndicateurValeurAjouteeCollegeDataset();
        dataset.setUai(uaiExiste);
        dataset.setAnnee(2025);
        datasets.add(dataset);

        service.createOrUpdateIVA(datasets);
        verify(coreEtablissementService).saveMetadata(any());
    }

    @Test
    void createOrUpdateJpoTest() {
        List<MasaJpoDataset> datasets = new ArrayList<>();
        MasaJpoDataset dataset = new MasaJpoDataset();
        dataset.setMasaId("123456");
        datasets.add(dataset);
        service.createOrUpdateJpo(datasets, "masa");
        verify(coreEtablissementService).saveJPO(any());
    }

}