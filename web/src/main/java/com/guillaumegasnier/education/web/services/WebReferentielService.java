package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDto;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface WebReferentielService {

    Optional<MetierDetailsDto> findMetier(@NonNull String code);
}
