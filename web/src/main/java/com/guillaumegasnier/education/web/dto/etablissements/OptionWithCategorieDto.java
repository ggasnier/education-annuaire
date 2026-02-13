package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionWithCategorieDto {

    private OptionEtablissement.Categorie categorie;

    private List<OptionDto> options;
}
