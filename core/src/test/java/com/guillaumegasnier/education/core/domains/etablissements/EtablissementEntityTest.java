package com.guillaumegasnier.education.core.domains.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtablissementEntityTest {

    EtablissementEntity entity;

    @BeforeEach
    void setUp() {
        entity = new EtablissementEntity();
    }

    @Test
    void sourceTest() {
        Set<String> sources = new HashSet<>();
        assertEquals(new HashSet<>(), entity.getSources());
        entity.setSources(sources);
        assertEquals(new HashSet<>(), entity.getSources());
        entity.addSource("en");
        sources.add("en");
        assertEquals(sources, entity.getSources());
    }

}