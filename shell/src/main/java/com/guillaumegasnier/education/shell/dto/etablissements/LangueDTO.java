package com.guillaumegasnier.education.shell.dto.etablissements;

import com.guillaumegasnier.education.core.enums.Langue;

public record LangueDTO(String uai, Langue langue, Langue.Categorie categorie, String enseignement) {
}
