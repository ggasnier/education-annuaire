package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactEtablissementDatasetTest {

    @Test
    void tel_valide_normalise() {
        var result = ContactEtablissementDataset.of(Contact.TEL, "01 02 03 04 05");
        assertTrue(result.isPresent());
        assertEquals("0102030405", result.get().valeur());
    }

    @Test
    void tel_invalide_retourne_vide() {
        assertTrue(ContactEtablissementDataset.of(Contact.TEL, "123").isEmpty());
    }

    @Test
    void email_valide() {
        var result = ContactEtablissementDataset.of(Contact.EMAIL, "contact@ecole.fr");
        assertTrue(result.isPresent());
        assertEquals("contact@ecole.fr", result.get().valeur());
    }

    @Test
    void email_invalide_retourne_vide() {
        assertTrue(ContactEtablissementDataset.of(Contact.EMAIL, "pasunemail").isEmpty());
    }

    @Test
    void web_accepte_sans_validation() {
        var result = ContactEtablissementDataset.of(Contact.WEB, "  https://ecole.fr  ");
        assertTrue(result.isPresent());
        assertEquals("https://ecole.fr", result.get().valeur());
    }
}