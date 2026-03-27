package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDto;
import com.guillaumegasnier.education.web.mappers.WebReferentielMapper;
import com.guillaumegasnier.education.web.services.WebReferentielService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebReferentielServiceImpl implements WebReferentielService {

    private final CoreReferentielService coreReferentielService;
    private final WebReferentielMapper webReferentielMapper;

    @Override
    public Optional<MetierDetailsDto> findMetier(@NonNull String code) {
        return coreReferentielService.findMetier(code).map(webReferentielMapper::toMetierDetailsDto);
    }
}
