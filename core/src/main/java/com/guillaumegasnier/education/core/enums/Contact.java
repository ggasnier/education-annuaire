package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Contact {
    TEL("Téléphone"),
    FAX("Fax"),
    WEB("Site web"),
    EMAIL("E-mail"),
    TWITTER("Twitter"),
    FACEBOOK("Facebook"),
    LINKEDIN("Linkedin"),
    YOUTUBE("Youtube"),
    WIKIPEDIA("Wikipedia");

    private final String nom;
}
