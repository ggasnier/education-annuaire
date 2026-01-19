package com.guillaumegasnier.education.core.domains.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EtablissementMetadataEntityTest {

    EtablissementMetadataEntity entity;

    @BeforeEach
    void setUp() {
        entity = new EtablissementMetadataEntity();
    }

    @Test
    void constructorTest() {
        EtablissementEntity etablissementEntity = new EtablissementEntity();
        etablissementEntity.setUai("1234567A");

        EtablissementAnneePK pk = new EtablissementAnneePK();
        pk.setUai("1234567A");
        pk.setAnnee(2026);

        entity = new EtablissementMetadataEntity(pk, etablissementEntity);
        assertNotNull(entity);
    }
}