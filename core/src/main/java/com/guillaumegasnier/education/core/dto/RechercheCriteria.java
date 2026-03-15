package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RechercheCriteria {

    private final static List<String> TYPES = List.of("etablissement", "formation", "certification", "metier");

    /**
     * Texte de la recherche
     */
    private String q;

    /**
     * Type de la recherche : etablissement par défaut
     */
    private String type = "etablissement";

    /**
     * Numéro de la page : 0 par défaut;
     */
    private int page = 0;

    /**
     * Résultats par page : 10 par défaut
     */
    private int size = 10;

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
            this.setType(filtresCopy.getFirst("type"));
            filtresCopy.remove("type");
        }

        this.filtres = filtresCopy;
    }

    public void setType(String type) {
        if (type == null)
            this.type = "etablissement";
        else if (TYPES.contains(type))
            this.type = type;
        else
            this.type = "etablissement";
    }

    public Boolean selected(String type) {
        log.info("selected:{}/{}", type, this.type);
        if (type == null) return false;
        if (this.type.equals(type)) return true;
        return false;
    }
}
