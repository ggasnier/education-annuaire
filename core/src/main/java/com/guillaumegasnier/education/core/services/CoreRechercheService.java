package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import com.guillaumegasnier.education.core.dto.RechercheResultatsDTO;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public interface CoreRechercheService {

    void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities);

    default RechercheResultatsDTO searchEtablissements(RechercheCriteria criteria) {
        return new RechercheResultatsDTO();
    }

    default List<RechercheFacetteDTO> searchEtablissementsFacettes(RechercheCriteria criteria) {
        return new ArrayList<>();
    }
}
