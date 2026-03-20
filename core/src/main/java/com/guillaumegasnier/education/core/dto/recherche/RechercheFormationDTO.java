package com.guillaumegasnier.education.core.dto.recherche;

import com.guillaumegasnier.education.core.domains.recherche.RechercheFormationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RechercheFormationDTO extends AbstractRechercheDTO {

    /**
     * Liste des résultats
     */
    private List<RechercheFormationEntity> resultats = new ArrayList<>();
}
