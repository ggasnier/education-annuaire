package com.guillaumegasnier.education.core.dto;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Data
public class CritereRecherchDto {

    private int page = 1;

    private int size = 20;

    private String q = "";

    private List<String> codeNature = new ArrayList<>();

    public CritereRecherchDto(MultiValueMap<String, String> facettes) {
        MultiValueMap<String, String> facettesCopy = new LinkedMultiValueMap<>(facettes);

        if (facettesCopy.get("page") != null && !facettesCopy.get("page").isEmpty() && Integer.parseInt(facettesCopy.get("page").getFirst()) > 0) {
            page = Integer.parseInt(facettesCopy.get("page").getFirst());
            facettesCopy.remove("page");
        }

        if (facettesCopy.get("q") != null && !facettesCopy.get("q").isEmpty()) {
            q = facettesCopy.get("q").getFirst().trim();
            facettesCopy.remove("q");
        }

        if (facettesCopy.get("codeNature") != null && !facettesCopy.get("codeNature").isEmpty()) {
            codeNature = facettesCopy.get("codeNature");
            facettesCopy.remove("codeNature");
        }
    }
}
