package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RechercheCriteria {

    /**
     * Texte de la recherche
     */
    private String q;


    private String type;

    /**
     * Numéro de la page
     */
    private int page = 0;

    /**
     * Filtres appliqués à la recherche
     */
    private MultiValueMap<String, String> filtres;

    public RechercheCriteria(MultiValueMap<String, String> filtres) {

        if (filtres == null) {
            this.filtres = new LinkedMultiValueMap<>();
            return;
        }

        MultiValueMap<String, String> filtresCopy = new LinkedMultiValueMap<>(filtres);

        if (filtresCopy.get("q") != null && !filtresCopy.get("q").isEmpty()) {
            this.q = filtresCopy.getFirst("q");
            filtresCopy.remove("q");
        }

        if (filtresCopy.get("page") != null && !filtresCopy.get("page").isEmpty()) {
            this.page = Integer.parseInt(Objects.requireNonNull(filtresCopy.getFirst("page")));
            filtresCopy.remove("page");
        }

        if (filtresCopy.get("type") != null && !filtresCopy.get("type").isEmpty()) {
            this.type = filtresCopy.getFirst("type");
            filtresCopy.remove("type");
        }

        this.filtres = filtresCopy;
    }
}
