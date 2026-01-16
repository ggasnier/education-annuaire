package com.guillaumegasnier.education.shell.dto.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;

public record ContactDTO(String uai, Contact contact, String valeur) {
}
