package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.NatureEntity;
import com.guillaumegasnier.education.core.domains.recherche.FacetteEntity;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.core.repositories.RechercheRepository;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.web.configuration.NomResolver;
import com.guillaumegasnier.education.web.dto.FacetteRechercheDto;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import com.guillaumegasnier.education.web.dto.ValeurFacetteRecherche;
import com.guillaumegasnier.education.web.enums.Categorie;
import com.guillaumegasnier.education.web.mappers.WebRechercheMapper;
import com.guillaumegasnier.education.web.services.RechercheService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RechercheServiceImpl implements RechercheService {

    private final WebRechercheMapper webRechercheMapper;
    private final RechercheRepository rechercheRepository;
    private final Map<Categorie, NomResolver> resolvers = new EnumMap<>(Categorie.class);
    private final CoreEtablissementService coreEtablissementService;

    @Autowired
    public RechercheServiceImpl(WebRechercheMapper webRechercheMapper, RechercheRepository rechercheRepository, CoreEtablissementService coreEtablissementService) {
        this.webRechercheMapper = webRechercheMapper;
        this.rechercheRepository = rechercheRepository;
        this.coreEtablissementService = coreEtablissementService;
    }

    @PostConstruct
    public void initResolvers() {
        resolvers.put(Categorie.CODE_CONTRAT, this::resolveNomContrat);
        resolvers.put(Categorie.CODE_NATURE, this::resolveNomNature);
        resolvers.put(Categorie.CATEGORIE, this::resolveNomCategorie);
        resolvers.put(Categorie.OPTION, this::resolveNomOption);
        resolvers.put(Categorie.SPECIALITE, this::resolveNomSpecialite);
    }

    @Cacheable(value = "natures", key = "code")
    public String resolveNomNature(String code) {
        return coreEtablissementService.findNature(code)
                .map(NatureEntity::getNom)
                .orElse("Inconnu");
    }

    @Cacheable("contrats")
    public String resolveNomContrat(String code) {
        return coreEtablissementService.findContrat(code)
                .map(ContratEntity::getNom)
                .orElse("Inconnu");
    }

    public String resolveNomCategorie(String code) {
        return Optional.of(Categorie.valueOf(code.toUpperCase(Locale.ROOT)))
                .map(Categorie::getNom)
                .orElse("Inconnu");
    }

    public String resolveNomOption(String code) {
        return Optional.of(OptionEtablissement.valueOf(code.toUpperCase(Locale.ROOT)))
                .map(OptionEtablissement::getNom)
                .orElse("Inconnu");
    }

    public String resolveNomSpecialite(String code) {
        return Optional.of(SpecialiteBac.valueOf(code.toUpperCase(Locale.ROOT)))
                .map(SpecialiteBac::getNom)
                .orElse("Inconnu");
    }

    @Override
    public List<ResultatRechercheDto> recherche(MultiValueMap<String, String> facettes) {

        MultiValueMap<String, String> facettesCopy = new LinkedMultiValueMap<>(facettes);

        int page = 1;
        String q = "";
        List<String> categories = new ArrayList<>();

        // On cherche la page
        if (facettesCopy.get("page") != null && !facettesCopy.get("page").isEmpty() && Integer.parseInt(facettesCopy.get("page").getFirst()) > 0) {
            page = Integer.parseInt(facettesCopy.get("page").getFirst());
            facettesCopy.remove("page");
        }

        // On cherche le texte
        if (facettesCopy.get("q") != null && !facettesCopy.get("q").isEmpty()) {
            q = facettesCopy.get("q").getFirst().trim();
            facettesCopy.remove("q");
        }

        // On cherche les catégories
        if (facettesCopy.get("categorie") != null && !facettesCopy.get("categorie").isEmpty()) {
            categories = facettesCopy.get("categorie");
            facettesCopy.remove("categorie");
        }

        log.debug("page: {}", page);
        log.debug("q: {}", q);
        log.debug("categories: {}", categories);

        return rechercheRepository.getResultats(q, page, categories, facettesCopy)
                .stream()
                .map(webRechercheMapper::toResultatRechercheDto).toList();
    }

    @Override
    public List<FacetteRechercheDto> facette(MultiValueMap<String, String> facettes) {

        MultiValueMap<String, String> facettesCopy = new LinkedMultiValueMap<>(facettes);
        String q = "";

        // On cherche le texte
        if (facettesCopy.get("q") != null && !facettesCopy.get("q").isEmpty()) {
            q = facettesCopy.get("q").getFirst().trim();
        }

        List<FacetteRechercheDto> resultats = new ArrayList<>();

        for (Map.Entry<String, List<FacetteEntity>> entry : rechercheRepository.getFacettes(q).stream()
                .filter(facetteEntity -> !facetteEntity.getCode().equals("code_commune"))
                .collect(Collectors.groupingBy(FacetteEntity::getCode)).entrySet()) {
            String code = entry.getKey();
            List<FacetteEntity> list = entry.getValue();

            FacetteRechercheDto facetteRechercheDto = new FacetteRechercheDto();
            facetteRechercheDto.setCodeCategorie(code);
            facetteRechercheDto.setNomCategorie(Categorie.valueOf(code.toUpperCase(Locale.ROOT)).getNom());

            List<ValeurFacetteRecherche> valeurs = list.stream().map(
                    facetteEntity -> {
                        ValeurFacetteRecherche valeurFacetteRecherche = new ValeurFacetteRecherche();
                        valeurFacetteRecherche.setCode(facetteEntity.getValeur());
                        valeurFacetteRecherche.setTotal(facetteEntity.getTotal());
                        valeurFacetteRecherche.setChecked(facettesCopy.containsKey(code) && facettesCopy.get(code).contains(facetteEntity.getValeur()));

                        return valeurFacetteRecherche;
                    }
            ).toList();

            facetteRechercheDto.setValeurs(valeurs);
            resultats.add(facetteRechercheDto);
        }

        return resultats.stream().map(this::toFacetteRechercheDto).toList();
    }

    private FacetteRechercheDto toFacetteRechercheDto(FacetteRechercheDto facetteDto) {
        NomResolver resolver = resolvers.get(Categorie.valueOf(facetteDto.getCodeCategorie().toUpperCase(Locale.ROOT)));
        if (resolver == null) {
            log.info("Pas de resolver pour {}", facetteDto.getCodeCategorie().toUpperCase(Locale.ROOT));
            return facetteDto;
        }

        facetteDto.setValeurs(
                facetteDto.getValeurs().stream()
                        .map(v -> {
                            v.setNom(resolver.resolveNom(v.getCode()));
                            return v;
                        })
                        .toList()
        );

        return facetteDto;
    }
}
