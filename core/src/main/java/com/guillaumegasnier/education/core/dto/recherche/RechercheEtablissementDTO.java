package com.guillaumegasnier.education.core.dto.recherche;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RechercheEtablissementDTO extends AbstractRechercheDTO {

    /**
     * Liste des résultats
     */
    private List<RechercheEtablissementEntity> resultats = new ArrayList<>();
}
