package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.OffresType;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import com.guillaumegasnier.education.shell.transformers.FormationTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ShellFormationServiceImplTest {

    @Mock
    private FormationMapper formationMapper;
    @Mock
    private CoreFormationService coreFormationService;
    @Mock
    private ValidatorService validatorService;
    @Mock
    private FormationTransformer formationTransformer;

    @InjectMocks
    private ShellFormationServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrUpdateOrganismesTest() {
        List<TravailOrganismeFormationDataset> datasets = new ArrayList<>();
        datasets.add(new TravailOrganismeFormationDataset());
        service.createOrUpdateOrganismes(datasets);
        verify(coreFormationService).saveOrganismes(any());
    }

    @Test
    void createOrUpdateFormationsOnisepEsrTest() {
        List<OnisepFormationDataset> datasets = new ArrayList<>();
        datasets.add(new OnisepFormationDataset());
        service.createOrUpdateFormationsOnisepEsr(datasets);
        verify(coreFormationService).saveFormations(any());
    }

    @Test
    void createOrUpdateFormationsCarifTest() {
        List<CarifFormationDataset> datasets = new ArrayList<>();
        datasets.add(new CarifFormationDataset());
        service.createOrUpdateFormationsCarif(datasets);
        verify(coreFormationService).saveFormations(any());
    }

    @Test
    void createOrUpdateFormationsParcoursupTest() {
        List<ParcoursupFormationDataset> datasets = new ArrayList<>();
        datasets.add(new ParcoursupFormationDataset());
        service.createOrUpdateFormationsParcoursup(datasets);
        verify(coreFormationService).saveFormations(any());
    }

    @Test
    void createOrUpdateFormationsOnisepLheoTest() {
        service.createOrUpdateFormationsOnisepLheo(null);
        verify(coreFormationService, times(0)).saveFormations(any());

        OffresType offresType = new OffresType();
        offresType.getFormation().add(new FormationType());
        LheoSubtype lheoSubtype = new LheoSubtype();
        lheoSubtype.setOffres(offresType);
        service.createOrUpdateFormationsOnisepLheo(lheoSubtype);
        verify(coreFormationService).saveFormations(any());
    }


}