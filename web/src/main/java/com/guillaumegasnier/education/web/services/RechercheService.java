package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.FacetteRechercheDto;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;

public interface RechercheService {

    ResultatRechercheDto recherche(MultiValueMap<String, String> facettes);

    List<FacetteRechercheDto> facette(MultiValueMap<String, String> facettes) throws IOException;
}
