package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.enums.Sport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EtablissementSportEntityTest {

    EtablissementSportEntity entity;

    @BeforeEach
    void setUp() {
        entity = new EtablissementSportEntity();
    }

    @Test
    void constructorTest() {
        EtablissementEntity etablissementEntity = new EtablissementEntity();
        etablissementEntity.setUai("1234567A");

        EtablissementAnneePK pk = new EtablissementAnneePK();
        pk.setUai("1234567A");
        pk.setAnnee(2026);

        entity = new EtablissementSportEntity("", Sport.SPORTS_NAUTIQUES, Sport.Categorie.SE, etablissementEntity);
        assertNotNull(entity);
    }
}