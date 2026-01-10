package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechercheCriteria {

    /**
     * Texte de la recherche
     */
    private String q;

    /**
     * Numéro de la page
     */
    private int page = 0;

    /**
     * Filtres appliqués à la recherche
     */
    private MultiValueMap<String, String> filtres;

    public RechercheCriteria(MultiValueMap<String, String> filtres) {

        if (filtres.get("q") != null && !filtres.get("q").isEmpty()) {
            this.q = filtres.getFirst("q");
            filtres.remove("q");
        }

        if (filtres.get("page") != null && !filtres.get("page").isEmpty()) {
            this.page = Integer.parseInt(Objects.requireNonNull(filtres.getFirst("page")));
            filtres.remove("page");
        }

        this.filtres = filtres;
    }
}
