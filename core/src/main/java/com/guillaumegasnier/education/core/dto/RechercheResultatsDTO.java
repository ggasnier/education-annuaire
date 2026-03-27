package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RechercheResultatsDTO {

    /**
     * Recherche textuelle
     * TODO : à protéger des injections SQL et XSS
     */
    private String q;

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
    
}
