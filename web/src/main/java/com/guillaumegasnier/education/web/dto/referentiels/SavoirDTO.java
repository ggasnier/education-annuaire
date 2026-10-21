package com.guillaumegasnier.education.web.dto.referentiels;

import com.guillaumegasnier.education.core.enums.OuiNon;

public record SavoirDTO(String nom,
                        String categorie,
                        String sousCategorie,
                        OuiNon transitionEco) {
}
