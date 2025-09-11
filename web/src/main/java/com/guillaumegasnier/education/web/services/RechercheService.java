package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.FacetteRechercheDto;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface RechercheService {

    List<ResultatRechercheDto> recherche(MultiValueMap<String, String> facettes);

    List<FacetteRechercheDto> facette(MultiValueMap<String, String> facettes);
}
