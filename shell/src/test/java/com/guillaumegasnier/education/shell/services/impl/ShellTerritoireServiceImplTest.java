package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.mappers.ReferenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ShellTerritoireServiceImplTest {

    private CoreTerritoireService coreTerritoireService;

    private ReferenceMapper referenceMapper;

    private ShellTerritoireServiceImpl service;

    @BeforeEach
    void setUp() {
        referenceMapper = Mappers.getMapper(ReferenceMapper.class);
        coreTerritoireService = Mockito.mock(CoreTerritoireService.class);
        service = new ShellTerritoireServiceImpl(coreTerritoireService, referenceMapper);
    }

    @Test
    void createOrUpdatePaysTest() {
        List<PaysDataset> datasets = new ArrayList<>();
        datasets.add(new PaysDataset());
        service.createOrUpdatePays(datasets);
        verify(coreTerritoireService).savePays(any());
    }

    @Test
    void createOrUpdateRegionsTest() {
        List<RegionDataset> datasets = new ArrayList<>();
        datasets.add(new RegionDataset());
        service.createOrUpdateRegions(datasets);
        verify(coreTerritoireService).saveRegions(any());

    }

    @Test
    void createOrUpdateAcademiesTest() {
        List<AcademieDataset> datasets = new ArrayList<>();
        datasets.add(new AcademieDataset());
        service.createOrUpdateAcademies(datasets);
        verify(coreTerritoireService).saveAcademies(any());
    }

    @Test
    void createOrUpdateDepartementsTest() {
        HashMap<String, String> codeAcademieMap = new HashMap<>();
        List<DepartementDataset> datasets = new ArrayList<>();
        datasets.add(new DepartementDataset());
        service.createOrUpdateDepartements(datasets, codeAcademieMap);
        verify(coreTerritoireService).saveDepartements(any());
    }

    @Test
    void createOrUpdateCommunesTest() {
        List<CommuneDataset> datasets = new ArrayList<>();
        CommuneDataset communeDataset = new CommuneDataset();
        communeDataset.setCodeDepartement("075");
        datasets.add(communeDataset);
        service.createOrUpdateCommunes(datasets);
        verify(coreTerritoireService).saveCommunes(any());
    }

}