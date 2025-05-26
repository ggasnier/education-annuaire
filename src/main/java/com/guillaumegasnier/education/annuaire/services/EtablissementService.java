package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import com.guillaumegasnier.education.annuaire.domains.NatureEntity;
import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.dto.IPSDto;
import com.guillaumegasnier.education.annuaire.mappers.EtablissementMapper;
import com.guillaumegasnier.education.annuaire.repositories.CommuneRepository;
import com.guillaumegasnier.education.annuaire.repositories.EtablissementRepository;
import com.guillaumegasnier.education.annuaire.repositories.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class EtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final CommuneRepository communeRepository;
    private final NatureRepository natureRepository;
    private final EtablissementMapper etablissementMapper;

    @Autowired
    public EtablissementService(EtablissementRepository etablissementRepository, CommuneRepository communeRepository, NatureRepository natureRepository, EtablissementMapper etablissementMapper) {
        this.etablissementRepository = etablissementRepository;
        this.communeRepository = communeRepository;
        this.natureRepository = natureRepository;
        this.etablissementMapper = etablissementMapper;
    }

    /**
     * <p>Création d'un établissement</p>
     *
     * @param dto Données d'entrées, à valider
     * @return Le Dto de l'établissement créé
     */
    public Optional<EtablissementDto> createEtablissement(@NonNull EtablissementRequestDto dto) {
        EtablissementEntity entity = etablissementMapper.toEntity(dto);
        entity.setNature(this.getNature(dto.getCodeNature()));
        entity.setCommune(this.getCommune(dto.getCodeCommune()));
        etablissementRepository.save(entity);
        return Optional.of(etablissementMapper.toDto(entity));
    }

    private CommuneEntity getCommune(String codeCommune) {
        if (codeCommune == null) return null;
        return communeRepository.getReferenceById(codeCommune);
    }

    private NatureEntity getNature(String codeNature) {
        if (codeNature == null) return null;
        return natureRepository.getReferenceById(codeNature);
    }

    public Optional<EtablissementDto> getEtablissement(@NonNull String uai) {
        return Optional.empty();
    }

    public Optional<EtablissementDto> updateEtablissement(@NonNull EtablissementRequestDto dto) {
        return Optional.empty();
    }

    public List<IPSDto> getEtablissementIPS(@NonNull String uai) {
        return Collections.emptyList();
    }

    public Optional<IPSDto> createOrUpdateIndice(@NonNull String uai, @NonNull IPSDto ips) {
        return Optional.empty();
    }

    public Page<EtablissementDto> searchEtablissement(int page) {
        Pageable pageable = PageRequest.of(page, 20);

        return etablissementRepository.findAll(pageable).map(etablissementMapper::toDto);
    }
}
