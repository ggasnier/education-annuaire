package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.repositories.RechercheRepository;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import com.guillaumegasnier.education.web.mappers.WebRechercheMapper;
import com.guillaumegasnier.education.web.services.RechercheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RechercheServiceImpl implements RechercheService {

    private final WebRechercheMapper webRechercheMapper;
    private final RechercheRepository rechercheRepository;

    @Autowired
    public RechercheServiceImpl(WebRechercheMapper webRechercheMapper, RechercheRepository rechercheRepository) {
        this.webRechercheMapper = webRechercheMapper;
        this.rechercheRepository = rechercheRepository;
    }

    @Override
    public List<ResultatRechercheDto> recherche(MultiValueMap<String, String> facettes) {

        int page = 1;
        String q = "";
        List<String> categories = new ArrayList<>();

        // On cherche la page
        if (facettes.get("page") != null && !facettes.get("page").isEmpty() && Integer.parseInt(facettes.get("page").getFirst()) > 0) {
            page = Integer.parseInt(facettes.get("page").getFirst());
            facettes.remove("page");
        }

        // On cherche le texte
        if (facettes.get("q") != null && !facettes.get("q").isEmpty()) {
            q = facettes.get("q").getFirst().trim();
            facettes.remove("q");
        }

        // On cherche les catégories
        if (facettes.get("categorie") != null && !facettes.get("categorie").isEmpty()) {
            categories = facettes.get("categorie");
            facettes.remove("categorie");
        }

        log.debug("page: {}", page);
        log.debug("q: {}", q);
        log.debug("categories: {}", categories);

        return rechercheRepository.getResultats(q, page, categories, facettes)
                .stream()
                .peek(resultatRecherche -> log.debug("resultatRecherche: {}", resultatRecherche))
                .map(webRechercheMapper::toResultatRechercheDto).toList();
    }
}
