package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportWithCategorieDTO {

    private Sport.Categorie categorie;

    private List<Sport> sections;

    @Getter
    @AllArgsConstructor
    public enum CategorieDTO {

        SS("Sections sportives"),
        SE("Sports études");

        private final String nom;
    }

}
