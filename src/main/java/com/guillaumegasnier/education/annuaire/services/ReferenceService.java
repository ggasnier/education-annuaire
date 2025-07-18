package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.annuaire.datasets.etablissements.EnEtablissementDataset;
import com.guillaumegasnier.education.annuaire.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.annuaire.datasets.references.*;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceService {

    private final CommuneRepository communeRepository;
    private final ReferenceMapper referenceMapper;
    private final NatureRepository natureRepository;
    private final AcademieRepository academieRepository;
    private final DepartementRepository departementRepository;
    private final RegionRepository regionRepository;
    private final ContratRepository contratRepository;

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
        entity.setCodePays(request.getCodePays());
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

    public String createOrUpdateDepartement(List<DepartementDataset> datasets, HashMap<String, String> lienDepartementAcademie) {
        datasets.forEach(dataset -> {
            var entity = importMapper.toDepartementEntity(dataset);
            if (dataset.getCodeRegion() != null && !dataset.getCodeRegion().isEmpty())
                entity.setRegion(regionRepository.getReferenceById(dataset.getCodeRegion()));

            var codeAcademie = lienDepartementAcademie.get(dataset.getCode());
            if (codeAcademie != null) {
                entity.setAcademie(academieRepository.getReferenceById(codeAcademie));
            }
            
            departementRepository.save(entity);
        });

        return "OK";
    }

    public String createOrUpdateCommune(List<CommuneDataset> datasets) {
        datasets.forEach(dataset -> {
            var entity = importMapper.toCommuneEntity(dataset);
            if (!dataset.getCodeDepartement().isEmpty()) {
                entity.setDepartement(departementRepository.getReferenceById(dataset.getCodeDepartement()));
            } else {
                entity.setDepartement(departementRepository.getReferenceById(dataset.getCode().substring(0, 2)));
            }
            communeRepository.save(entity);
        });

        return "TODO";
    }

    public String createOrUpdateNature(@NonNull List<NatureDataset> datasets) {
        datasets.forEach(dataset -> {
            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
                natureRepository.save(importMapper.toNatureEntity(dataset));
            }
        });

        return "OK";
    }

    public String createOrUpdateContrat(@NonNull List<ContratDataset> datasets) {
        datasets.forEach(dataset -> {
            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
                contratRepository.save(importMapper.toContratEntity(dataset));
            }
        });

        return "OK";
    }

    public String createOrUpdateAcademie(@NonNull List<AcademieDataset> datasets) {
        datasets.forEach(dataset -> {
            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
                academieRepository.save(importMapper.toAcademieEntity(dataset));
            }
        });

        return "OK";
    }

    public HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets) {
        HashMap<String, String> departments = new HashMap<>();
        datasets.forEach(
                dataset -> {
                    if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
                        departments.computeIfAbsent(dataset.getCodeDepartement(), k -> dataset.getCodeAcademie());
                    }
                }
        );
        return departments;
    }
}
