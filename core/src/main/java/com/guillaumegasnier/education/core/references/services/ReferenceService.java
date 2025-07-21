package com.guillaumegasnier.education.core.references.services;


import com.guillaumegasnier.education.core.references.entities.AcademieEntity;
import com.guillaumegasnier.education.core.references.entities.CommuneEntity;
import com.guillaumegasnier.education.core.references.entities.DepartementEntity;
import com.guillaumegasnier.education.core.references.entities.RegionEntity;
import com.guillaumegasnier.education.core.references.mappers.ReferenceMapper;
import com.guillaumegasnier.education.core.references.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceService implements IReferenceService {

    private final PaysRepository paysRepository;
    private final RegionRepository regionRepository;
    private final DepartementRepository departementRepository;
    private final CommuneRepository communeRepository;
    private final AcademieRepository academieRepository;

    private final ReferenceMapper referenceMapper;

//    private final NatureRepository natureRepository;
//    private final ContratRepository contratRepository;

//    private final ImportMapper importMapper;

    private final Map<Class<?>, Function<?, ?>> mappers = new HashMap<>();


    public String createOrUpdateAcademies(@NonNull List<AcademieEntity> entities) {
        academieRepository.saveAll(entities);
        return "OK";
    }

    public String createOrUpdateRegions(@NonNull List<RegionEntity> entities) {
        regionRepository.saveAll(entities);
        return "OK";
    }

    public void saveAcademies(List<AcademieEntity> entities) {
        academieRepository.saveAll(entities);
    }

    public void saveRegions(List<RegionEntity> entities) {
        regionRepository.saveAll(entities);
    }

    public void saveDepartements(List<DepartementEntity> entities) {
        departementRepository.saveAll(entities);
    }

    public RegionEntity getRegion(String code) {
        return regionRepository.findById(code).orElse(null);
    }

    public AcademieEntity getAcademie(String code) {
        return academieRepository.findById(code).orElse(null);

    }

    /**
     * à mettre en cache
     *
     * @param code
     * @return
     */
    public DepartementEntity getDepartement(String code) {
        return departementRepository.findById(code).orElse(null);
    }

    public void saveCommunes(List<CommuneEntity> entities) {
        communeRepository.saveAll(entities);
    }


//    public String createOrUpdateDepartements(List<DepartementDto> dtoList) {
//        dtoList.forEach(dto -> {
//            var entity = referenceMapper.toEntity(dto);
//
//            if (dto.getCodeRegion() != null && !dto.getCodeRegion().isEmpty())
//                entity.setRegion(regionRepository.getReferenceById(dto.getCodeRegion()));
//
//            var codeAcademie = lienDepartementAcademie.get(dto.getCode());
//
//            if (codeAcademie != null) {
//                entity.setAcademie(academieRepository.getReferenceById(codeAcademie));
//            }
//
//            departementRepository.save(entity);
//        });
//
//        return "OK";
//    }

//    @PostConstruct
//    void init() {
//        mappers.put(EnEtablissementDataset.class, (Function<EnEtablissementDataset, EtablissementEntity>) importMapper::toEtablissementEntity);
//        mappers.put(RegionDataset.class, (Function<RegionDataset, RegionEntity>) importMapper::toRegionEntity);
//        mappers.put(DepartementDataset.class, (Function<DepartementDataset, DepartementEntity>) importMapper::toDepartementEntity);
//    }


//    public Optional<CommuneDto> createCommune(CommuneRequestDto request) {
//        CommuneEntity entity = referenceMapper.toCommuneEntity(request);
//        entity.setCodePays(request.getCodePays());
//        communeRepository.save(entity);
//        return Optional.of(referenceMapper.toCommuneDto(entity));
//    }
//
//    public Page<CommuneDto> searchCommune(int page) {
//        Pageable pageable = PageRequest.of(page, 100);
//        return communeRepository.findAll(pageable).map(referenceMapper::toCommuneDto);
//    }
//
////    public List<NatureDto> getNatures() {
////        return referenceMapper.toNatureDto(natureRepository.findAllByOrderByCode());
////    }
//
//    public List<AcademieDto> getAcademies() {
//        return referenceMapper.toAcademieDto(academieRepository.findAllByOrderByCode());
//    }
//
//    public List<DepartementDto> getDepartements() {
//        return referenceMapper.toDepartementDto(departementRepository.findAllByOrderByCode());
//    }
//
//    public Optional<CommuneDto> getCommune(String code) {
//        return communeRepository.findById(code).map(referenceMapper::toCommuneDto);
//    }
//
//    public List<RegionDto> getRegions() {
//        return referenceMapper.toRegionDto(regionRepository.findAllByOrderByCode());
//    }
//
//    public String createOrUpdateRegion(List<RegionDataset> datasets) {
//
//        datasets.forEach(dataset -> {
//            regionRepository.save(importMapper.toRegionEntity(dataset));
//        });
//
//        return "OK";
//    }
//
//    public String createOrUpdateDepartement(List<DepartementDataset> datasets, HashMap<String, String> lienDepartementAcademie) {
//        datasets.forEach(dataset -> {
//            var entity = importMapper.toDepartementEntity(dataset);
//            if (dataset.getCodeRegion() != null && !dataset.getCodeRegion().isEmpty())
//                entity.setRegion(regionRepository.getReferenceById(dataset.getCodeRegion()));
//
//            var codeAcademie = lienDepartementAcademie.get(dataset.getCode());
//            if (codeAcademie != null) {
//                entity.setAcademie(academieRepository.getReferenceById(codeAcademie));
//            }
//
//            departementRepository.save(entity);
//        });
//
//        return "OK";
//    }
//
//    public String createOrUpdateCommune(List<CommuneDataset> datasets) {
//        datasets.forEach(dataset -> {
//            var entity = importMapper.toCommuneEntity(dataset);
//            if (!dataset.getCodeDepartement().isEmpty()) {
//                entity.setDepartement(departementRepository.getReferenceById(dataset.getCodeDepartement()));
//            } else {
//                entity.setDepartement(departementRepository.getReferenceById(dataset.getCode().substring(0, 2)));
//            }
//            communeRepository.save(entity);
//        });
//
//        return "TODO";
//    }
//
//    public String createOrUpdateNature(@NonNull List<NatureDataset> datasets) {
//        datasets.forEach(dataset -> {
//            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
//                natureRepository.save(importMapper.toNatureEntity(dataset));
//            }
//        });
//
//        return "OK";
//    }
//
//    public String createOrUpdateContrat(@NonNull List<ContratDataset> datasets) {
//        datasets.forEach(dataset -> {
//            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
//                contratRepository.save(importMapper.toContratEntity(dataset));
//            }
//        });
//
//        return "OK";
//    }
//
//    public String createOrUpdateAcademie(@NonNull List<AcademieDataset> datasets) {
//        datasets.forEach(dataset -> {
//            if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
//                academieRepository.save(importMapper.toAcademieEntity(dataset));
//            }
//        });
//
//        return "OK";
//    }
//
//    public HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets) {
//        HashMap<String, String> departments = new HashMap<>();
//        datasets.forEach(
//                dataset -> {
//                    if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
//                        departments.computeIfAbsent(dataset.getCodeDepartement(), k -> dataset.getCodeAcademie());
//                    }
//                }
//        );
//        return departments;
//    }
}
