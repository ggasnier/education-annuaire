package com.guillaumegasnier.education.core.dto.recherche;

import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RechercheMetierDTO extends AbstractRechercheDTO {

    /**
     * Liste des résultats
     */
    private List<RechercheMetierEntity> resultats = new ArrayList<>();

}
