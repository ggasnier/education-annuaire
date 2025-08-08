package com.guillaumegasnier.education.shell.services.impl;


import com.guillaumegasnier.education.core.domains.*;
import com.guillaumegasnier.education.core.repositories.*;
import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.mappers.ReferenceMapper;
import com.guillaumegasnier.education.shell.services.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private final ReferenceMapper referenceMapper;

    @Autowired
    public ReferenceServiceImpl(PaysRepository paysRepository, RegionRepository regionRepository, DepartementRepository departementRepository, CommuneRepository communeRepository, AcademieRepository academieRepository, ReferenceMapper referenceMapper) {
        this.paysRepository = paysRepository;
        this.regionRepository = regionRepository;
        this.departementRepository = departementRepository;
        this.communeRepository = communeRepository;
        this.academieRepository = academieRepository;
        this.referenceMapper = referenceMapper;
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

    /// /    public List<NatureDto> getNatures() {
    /// /        return referenceMapper.toNatureDto(natureRepository.findAllByOrderByCode());
    /// /    }
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
    @Override
    public String createOrUpdatePays(@NonNull List<PaysDataset> datasets) {
        return "";
    }

    @Override
    public String createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets) {
        this.saveAcademies(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(referenceMapper::toAcademieEntity)
                .toList());
        return String.format("Import terminé : %d académie(s) enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateRegions(@NonNull List<RegionDataset> datasets) {
        this.saveRegions(datasets.stream()
                .map(referenceMapper::toRegionEntity)
                .toList());
        return String.format("Import terminé : %d région(s) enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap) {
        List<DepartementEntity> departements = datasets.stream()
                .map(dataset -> {
                    DepartementEntity entity = referenceMapper.toDepartementEntity(dataset);

                    // Association à une région si le code est renseigné
                    String codeRegion = dataset.getCodeRegion();
                    if (codeRegion != null && !codeRegion.isEmpty()) {
                        entity.setRegion(this.getRegion(codeRegion));
                    }

                    // Association à une académie si le mapping existe
                    String codeAcademie = codeAcademieMap.get(dataset.getCode());
                    if (codeAcademie != null && !codeAcademie.isEmpty()) {
                        entity.setAcademie(this.getAcademie(codeAcademie));
                    }

                    return entity;
                })
                .toList();

        this.saveDepartements(departements);

        return String.format("Import terminé : %d département(s) enregistré(s).", departements.size());
    }

    @Override
    public String createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets) {
        this.saveCommunes(datasets.stream().map(dataset -> {
            var entity = referenceMapper.toCommuneEntity(dataset);
            if (!dataset.getCodeDepartement().isEmpty()) {
                entity.setDepartement(this.getDepartement(dataset.getCodeDepartement()));
            } else {
                entity.setDepartement(this.getDepartement(dataset.getCode().substring(0, 2)));
            }

            return entity;
        }).toList());

        return String.format("Import terminé : %d communes(s) enregistré(s).", datasets.size());
    }

    @Override
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
