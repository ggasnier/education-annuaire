package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.core.enums.CoeurMetier;
import com.guillaumegasnier.education.web.dto.referentiels.CompetenceDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDTO;
import com.guillaumegasnier.education.web.dto.referentiels.SavoirDTO;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WebReferentielService {

    Optional<MetierDetailsDTO> findMetier(@NonNull String code);

    Map<String, List<SavoirDTO>> getMetierSavoirList(@NonNull String code);

    List<MetierDTO> getAllMetiers();

    long getNbrCertifications();

    long getNbrCompetences();

    long getNbrMetiers();

    Map<CoeurMetier, Map<String, List<CompetenceDTO>>> getMetierCompetenceList(String code);
}
