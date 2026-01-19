package com.guillaumegasnier.education.core.domains.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EtablissementIdentifiantEntityTest {

    EtablissementIdentifiantEntity entity;

    @BeforeEach
    void setUp() {
        entity = new EtablissementIdentifiantEntity();
    }

    @Test
    void constructorTest() {
        EtablissementEntity etablissementEntity = new EtablissementEntity();
        etablissementEntity.setUai("1234567A");
        entity = new EtablissementIdentifiantEntity(etablissementEntity, "clef", " valeur");
        assertNotNull(entity);
    }

}