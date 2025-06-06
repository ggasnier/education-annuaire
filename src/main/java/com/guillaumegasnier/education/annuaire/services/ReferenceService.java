package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.dto.CommuneDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneRequestDto;
import com.guillaumegasnier.education.annuaire.mappers.ReferenceMapper;
import com.guillaumegasnier.education.annuaire.repositories.CommuneRepository;
import com.guillaumegasnier.education.annuaire.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReferenceService {

    private final PaysRepository paysRepository;
    private final CommuneRepository communeRepository;
    private final ReferenceMapper referenceMapper;

    @Autowired
    public ReferenceService(PaysRepository paysRepository, CommuneRepository communeRepository, ReferenceMapper referenceMapper) {
        this.paysRepository = paysRepository;
        this.communeRepository = communeRepository;
        this.referenceMapper = referenceMapper;
    }

    public Optional<CommuneDto> createCommune(CommuneRequestDto request) {
        CommuneEntity entity = referenceMapper.toEntity(request);
        entity.setPays(paysRepository.getReferenceById(request.getCodePays()));
        communeRepository.save(entity);
        return Optional.of(referenceMapper.toDto(entity));
    }

    public Page<CommuneDto> searchCommune(int page) {
        Pageable pageable = PageRequest.of(page, 100);
        return communeRepository.findAll(pageable).map(referenceMapper::toDto);
    }
}
