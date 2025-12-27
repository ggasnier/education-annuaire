package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactEtablissementDataset {

    private Contact contact;

    private String valeur;

    public ContactEtablissementDataset(Contact contact, String valeur) {
        switch (contact) {
            case TEL:
            case FAX:
            case WEB:
            case EMAIL:
            case TWITTER:
            case FACEBOOK:
            case LINKEDIN:
            case YOUTUBE:
            case WIKIPEDIA:
                this.contact = contact;
                this.valeur = valeur.trim();
                break;
        }
    }

}
