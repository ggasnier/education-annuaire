package com.guillaumegasnier.education.core.dto.recherche;

import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RechercheMetierDTO {
    /**
     * Recherche textuelle
     * TODO : à protéger des injections SQL et XSS
     */
    private String q;

    /**
     * Ce qu'on recherche (établisements, formations, métiers, etc...)
     */
    private String type = "metier";

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
    private List<RechercheMetierEntity> resultats = new ArrayList<>();

    /**
     * Liste des facettes
     */
    private List<RechercheFacetteDTO> facettes = new ArrayList<>();
    
}
