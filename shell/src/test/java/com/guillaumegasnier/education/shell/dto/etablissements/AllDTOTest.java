package com.guillaumegasnier.education.shell.dto.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.Sport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.guillaumegasnier.education.core.enums.OptionEtablissement.SECTION_SPORT;
import static com.guillaumegasnier.education.core.enums.SpecialiteBac.CINEMA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AllDTOTest {

    String uai = "1234567A";

    @BeforeEach
    void setUp() {
    }

    @Test
    void contactDTOTest() {
        var dto = new ContactDTO(uai, Contact.EMAIL, "lorem@ipsum.fr");
        assertEquals(uai, dto.uai());
        assertEquals(Contact.EMAIL, dto.contact());
        assertEquals("lorem@ipsum.fr", dto.valeur());
    }

    @Test
    void sportDTOTest() {
        var dto = new SportDTO(uai, Sport.SPORTS_NAUTIQUES, Sport.Categorie.SE);
        assertEquals(uai, dto.uai());
        assertEquals(Sport.SPORTS_NAUTIQUES, dto.sport());
        assertEquals(Sport.Categorie.SE, dto.categorie());
    }

    @Test
    void optionDTOTest() {
        var dto = new OptionDTO(uai, SECTION_SPORT, null);
        assertEquals(uai, dto.uai());
        assertEquals(SECTION_SPORT, dto.option());
    }

    @Test
    void specialiteDTOTest() {
        var dto = new SpecialiteDTO(uai, CINEMA);
        assertEquals(uai, dto.uai());
        assertEquals(CINEMA, dto.specialite());
    }

    @Test
    void masaDTOTest() {
        var dto = new MasaDTO(uai, "00123456");
        assertEquals(uai, dto.uai());
        assertEquals("00123456", dto.masaId());
    }

    @Test
    void identifiantDTOTest() {
        var dto = new IdentifiantDTO("lorem", "ipsum");
        assertEquals("lorem", dto.clef());
        assertEquals("ipsum", dto.valeur());
    }

    @Test
    void langueDTOTest() {
        var dto = new LangueDTO(uai, Langue.BR, Langue.Categorie.LV, "lv1"); // Vive la Bretagne
        assertEquals(uai, dto.uai());
        assertEquals(Langue.BR, dto.langue());
        assertEquals(Langue.Categorie.LV, dto.categorie());
        assertEquals("lv1", dto.enseignement());
    }
}