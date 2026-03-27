package com.guillaumegasnier.education.core.domains.etablissements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NatureEntityTest {

    @Test
    void testNatureEntity() {
        NatureEntity entity = new NatureEntity("666", "Lorem Ipsum");
        assertEquals("666", entity.getCode());
        assertNull(entity.getNomCourt());
    }

    @Test
    void testNatureEntityEquals() {
        NatureEntity entity = new NatureEntity("666", "Lorem Ipsum");
        NatureEntity entity2 = new NatureEntity("666", "Lorem Ipsum");
        assertEquals(entity, entity2);
        assertEquals(entity.hashCode(), entity2.hashCode());
        assertNotEquals(new NatureEntity(), entity);
    }
}