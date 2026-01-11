package com.guillaumegasnier.education.shell.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatJPODataset;
import static org.junit.jupiter.api.Assertions.*;

class ShellUtilTest {

    @Test
    void formatJPODatasetTestNull() {
        var output = formatJPODataset("0333596E", "");
        assertNull(output);

        var output2 = formatJPODataset("0333597F", null);
        assertNull(output2);
    }

    @Test
    void formatJPODatasetTest() {
        // Format basique
        var input = "le 24/01/2026 de 10h00 à 17h00";
        var output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-01-24"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-01-24"), output.getDateFin());
        assertEquals(LocalTime.parse("10:00:00"), output.getHeureDebut());
        assertEquals(LocalTime.parse("17:00:00"), output.getHeureFin());
        assertNull(output.getCommentaire());

        // Format plus simple
        input = "le 14/03/2026";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateFin());
        assertNull(output.getHeureDebut());
        assertNull(output.getHeureFin());
        assertNull(output.getCommentaire());

        // Format plus simple avec commentaire
        input = "le 14/03/2026 (post 3e) voir https://citesciencesvertes.educagri.fr/journees-portes-ouvertes/";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateFin());
        assertNull(output.getHeureDebut());
        assertNull(output.getHeureFin());
        assertEquals("(post 3e) voir https://citesciencesvertes.educagri.fr/journees-portes-ouvertes/", output.getCommentaire());

        // Format plus simple avec commentaire
        input = "le 14/03/2026 ,(post 3e) voir https://citesciencesvertes.educagri.fr/journees-portes-ouvertes/";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-03-14"), output.getDateFin());
        assertNull(output.getHeureDebut());
        assertNull(output.getHeureFin());
        assertEquals("(post 3e) voir https://citesciencesvertes.educagri.fr/journees-portes-ouvertes/", output.getCommentaire());

        // Avec commentaire
        input = "le 24/01/2026 de 09h00 à 12h00 Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", output.getCommentaire());

        // Avec une virgule en commentaire
        input = "le 24/01/2026 de 09h00 à 12h00, voir Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals("voir Lorem ipsum dolor sit amet, consectetur adipiscing elit.", output.getCommentaire());

        // Format multi-jours avec heures
        input = "du 06/02/2026 au 07/02/2026 de 09h00 à 17h00";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-02-06"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-02-07"), output.getDateFin());
        assertEquals(LocalTime.parse("09:00:00"), output.getHeureDebut());
        assertEquals(LocalTime.parse("17:00:00"), output.getHeureFin());

        // Format multi-jours avec commentaire
        input = "du 06/02/2026 au 08/02/2026 de 10h00 à 16h00, inscription obligatoire";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-02-06"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-02-08"), output.getDateFin());
        assertEquals(LocalTime.parse("10:00:00"), output.getHeureDebut());
        assertEquals(LocalTime.parse("16:00:00"), output.getHeureFin());
        assertEquals("inscription obligatoire", output.getCommentaire());

        // Format multi-jours sans heures
        input = "du 01/01/2026 au 31/12/2026";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-01-01"), output.getDateDebut());
        assertEquals(LocalDate.parse("2026-12-31"), output.getDateFin());
        assertNull(output.getHeureDebut());
        assertNull(output.getHeureFin());

    }

    @Test
    void formatJPODatasetTest2() {
        var input = "du 06/03/2026 au 07/03/2026";
        var output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-03-06"), output.getDateDebut());

        input = "du 06/03/2026 au 07/03/2026 (de 13h30 à 18h le vendredi, de 9h à 13h le samedi, toutes les formations)";
        output = formatJPODataset("0333596E", input);
        assertNotNull(output);
        assertEquals(LocalDate.parse("2026-03-06"), output.getDateDebut());
        assertNotNull(output.getCommentaire());
    }

}