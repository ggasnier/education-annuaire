package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.datasets.DepartementDataset;
import com.guillaumegasnier.education.annuaire.datasets.EnEtablissementDataset;
import com.guillaumegasnier.education.annuaire.datasets.RegionDataset;
import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.domains.DepartementEntity;
import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import com.guillaumegasnier.education.annuaire.domains.RegionEntity;
import com.guillaumegasnier.education.annuaire.dto.*;
import com.guillaumegasnier.education.annuaire.mappers.ImportMapper;
import com.guillaumegasnier.education.annuaire.mappers.ReferenceMapper;
import com.guillaumegasnier.education.annuaire.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ReferenceService {

    private final PaysRepository paysRepository;
    private final CommuneRepository communeRepository;
    private final ReferenceMapper referenceMapper;
    private final NatureRepository natureRepository;
    private final AcademieRepository academieRepository;
    private final DepartementRepository departementRepository;
    private final RegionRepository regionRepository;

    private final ImportMapper importMapper;

    private final Map<Class<?>, Function<?, ?>> mappers = new HashMap<>();

    @PostConstruct
    void init() {
        mappers.put(EnEtablissementDataset.class, (Function<EnEtablissementDataset, EtablissementEntity>) importMapper::toEtablissementEntity);
        mappers.put(RegionDataset.class, (Function<RegionDataset, RegionEntity>) importMapper::toRegionEntity);
        mappers.put(DepartementDataset.class, (Function<DepartementDataset, DepartementEntity>) importMapper::toDepartementEntity);
    }


    public Optional<CommuneDto> createCommune(CommuneRequestDto request) {
        CommuneEntity entity = referenceMapper.toCommuneEntity(request);
        entity.setPays(paysRepository.getReferenceById(request.getCodePays()));
        communeRepository.save(entity);
        return Optional.of(referenceMapper.toCommuneDto(entity));
    }

    public Page<CommuneDto> searchCommune(int page) {
        Pageable pageable = PageRequest.of(page, 100);
        return communeRepository.findAll(pageable).map(referenceMapper::toCommuneDto);
    }

    public List<NatureDto> getNatures() {
        return referenceMapper.toNatureDto(natureRepository.findAllByOrderByCode());
    }

    public List<AcademieDto> getAcademies() {
        return referenceMapper.toAcademieDto(academieRepository.findAllByOrderByCode());
    }

    public List<DepartementDto> getDepartements() {
        return referenceMapper.toDepartementDto(departementRepository.findAllByOrderByCode());
    }

    public Optional<CommuneDto> getCommune(String code) {
        return communeRepository.findById(code).map(referenceMapper::toCommuneDto);
    }

    public List<RegionDto> getRegions() {
        return referenceMapper.toRegionDto(regionRepository.findAllByOrderByCode());
    }

    public String createOrUpdateRegion(List<RegionDataset> datasets) {

        datasets.forEach(dataset -> {
            regionRepository.save(importMapper.toRegionEntity(dataset));
        });

        return "OK";
    }

    public String createOrUpdateDepartement(List<DepartementDataset> datasets) {

        datasets.forEach(dataset -> {
            var entity = importMapper.toDepartementEntity(dataset);
            entity.setRegion(regionRepository.getReferenceById(dataset.getCodeRegion()));
            departementRepository.save(entity);
        });

        return "OK";
    }
}
