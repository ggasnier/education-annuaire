package com.guillaumegasnier.education.web.dto.etablissements;


import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.web.dto.LangueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LangueWithCategorieDto {

    private Langue.Categorie categorie;

    private List<LangueDto> langues;
}
