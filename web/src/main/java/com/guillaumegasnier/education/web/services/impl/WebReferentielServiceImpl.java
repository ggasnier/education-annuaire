package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.enums.CoeurMetier;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.web.dto.referentiels.CompetenceDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDTO;
import com.guillaumegasnier.education.web.dto.referentiels.SavoirDTO;
import com.guillaumegasnier.education.web.mappers.WebReferentielMapper;
import com.guillaumegasnier.education.web.services.WebReferentielService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebReferentielServiceImpl implements WebReferentielService {

    private final CoreReferentielService coreReferentielService;
    private final WebReferentielMapper webReferentielMapper;

    @Override
    public Optional<MetierDetailsDTO> findMetier(@NonNull String code) {
        return coreReferentielService.findMetier(code).map(webReferentielMapper::toMetierDetailsDTO);
    }

    @Override
    public List<MetierDTO> getAllMetiers() {
        return coreReferentielService.findAll().stream().map(webReferentielMapper::toMetierDTO).toList();
    }

    @Override
    public long getNbrCertifications() {
        return coreReferentielService.getNbrCertifications();
    }

    @Override
    public long getNbrCompetences() {
        return coreReferentielService.getNbrCompetences();
    }

    @Override
    public long getNbrMetiers() {
        return coreReferentielService.getNbrMetiers();
    }

    @Override
    public Map<String, List<SavoirDTO>> getMetierSavoirList(@NonNull String code) {
        return coreReferentielService.getMetierSavoirList(code).stream().map(webReferentielMapper::toSavoirDTO)
                .collect(Collectors.groupingBy(SavoirDTO::categorie, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public Map<CoeurMetier, Map<String, List<CompetenceDTO>>> getMetierCompetenceList(String code) {
        return coreReferentielService.getMetierCompetenceList(code).stream().map(webReferentielMapper::toCompetenceDTO)
                .collect(Collectors.groupingBy(CompetenceDTO::coeurMetier, LinkedHashMap::new,
                        Collectors.groupingBy(CompetenceDTO::categorie, LinkedHashMap::new, Collectors.toList())));
    }

}
