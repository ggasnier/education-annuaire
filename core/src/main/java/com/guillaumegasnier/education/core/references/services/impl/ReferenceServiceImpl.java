package com.guillaumegasnier.education.core.references.services.impl;


import com.guillaumegasnier.education.core.references.entities.*;
import com.guillaumegasnier.education.core.references.repositories.*;
import com.guillaumegasnier.education.core.references.services.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReferenceServiceImpl implements ReferenceService {

    private final PaysRepository paysRepository;
    private final RegionRepository regionRepository;
    private final DepartementRepository departementRepository;
    private final CommuneRepository communeRepository;
    private final AcademieRepository academieRepository;

    @Autowired
    public ReferenceServiceImpl(PaysRepository paysRepository, RegionRepository regionRepository, DepartementRepository departementRepository, CommuneRepository communeRepository, AcademieRepository academieRepository) {
        this.paysRepository = paysRepository;
        this.regionRepository = regionRepository;
        this.departementRepository = departementRepository;
        this.communeRepository = communeRepository;
        this.academieRepository = academieRepository;
    }

    @Override
    public void savePays(List<PaysEntity> entities) {
        paysRepository.saveAll(entities);
    }

    @Override
    public void saveAcademies(List<AcademieEntity> entities) {
        academieRepository.saveAll(entities);
    }

    @Override
    public void saveRegions(List<RegionEntity> entities) {
        regionRepository.saveAll(entities);
    }

    @Override
    public void saveDepartements(List<DepartementEntity> entities) {
        departementRepository.saveAll(entities);
    }

    @Override
    public void saveCommunes(List<CommuneEntity> entities) {
        communeRepository.saveAll(entities);
    }

    @Override
    @Cacheable("region")
    public RegionEntity getRegion(String codeRegion) {
        return regionRepository.findById(codeRegion).orElse(null);
    }

    @Override
    @Cacheable("academie")
    public AcademieEntity getAcademie(String codeAcademie) {
        return academieRepository.findById(codeAcademie).orElse(null);
    }

    @Override
    @Cacheable("departement")
    public DepartementEntity getDepartement(String codeDepartement) {
        return departementRepository.findById(codeDepartement).orElse(null);
    }

    @Override
    @Cacheable("commune")
    public Optional<CommuneEntity> findCommune(String codeCommune) {
        return communeRepository.findById(codeCommune);
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
