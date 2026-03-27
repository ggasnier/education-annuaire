package com.guillaumegasnier.education.core.dto.recherche;

import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class AbstractRechercheDTO {

    /**
     * Recherche textuelle
     * TODO : à protéger des injections SQL et XSS
     */
    protected String q;

    /**
     * Ce qu'on recherche (établisements, formations, métiers, etc...)
     */
    protected String type = "metier";

    /**
     * Nombre total d'éléments trouvés
     */
    protected long total;

    /**
     * Page des résultats
     */
    protected int page;

    /**
     * Liste des facettes
     */
    protected List<RechercheFacetteDTO> facettes = new ArrayList<>();
}
