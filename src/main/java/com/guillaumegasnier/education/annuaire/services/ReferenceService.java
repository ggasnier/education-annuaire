package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.dto.*;
import com.guillaumegasnier.education.annuaire.mappers.ReferenceMapper;
import com.guillaumegasnier.education.annuaire.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
