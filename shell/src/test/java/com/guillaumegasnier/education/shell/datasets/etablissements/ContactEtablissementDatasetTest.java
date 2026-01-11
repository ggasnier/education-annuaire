package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactEtablissementDatasetTest {

    ContactEtablissementDataset dataset;

    @Test
    void constructorTest() {
        dataset = new ContactEtablissementDataset(Contact.TEL, "0102030405");
        assertEquals("0102030405", dataset.getValeur());
    }
}