package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Contient tous les résultats de recherche, les facettes, etc...
 */
@Data
public class RechercheDTO {

    /**
     * Recherche textuelle
     * TODO : à protéger des injections SQL et XSS
     */
    private String q;

    /**
     * Ce qu'on recherche (établisements, formations, métiers, etc...)
     */
    private String type;

    /**
     * Nombre total d'éléments trouvés
     */
    private long total;

    /**
     * Page des résultats
     */
    private int page;

    /**
     * Liste des résultats
     */
    private List<RechercheEtablissementEntity> resultats = new ArrayList<>();

    /**
     * Liste des facettes
     */
    private List<RechercheFacetteDTO> facettes = new ArrayList<>();
}
