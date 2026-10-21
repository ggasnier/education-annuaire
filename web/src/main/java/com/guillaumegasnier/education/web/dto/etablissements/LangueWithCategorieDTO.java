package com.guillaumegasnier.education.web.dto.etablissements;


import com.guillaumegasnier.education.core.enums.Langue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LangueWithCategorieDTO {

    private Langue.Categorie categorie;

    private List<LangueDTO> langues;
}
