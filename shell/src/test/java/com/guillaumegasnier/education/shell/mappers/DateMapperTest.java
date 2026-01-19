package com.guillaumegasnier.education.shell.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.guillaumegasnier.education.shell.mappers.DateMapper.toLocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateMapperTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toLocalDateTest() {
        assertEquals(LocalDate.parse("2026-01-01"), toLocalDate("01/01/2026"));
        assertNull(toLocalDate("2026-01-01"));
    }
}