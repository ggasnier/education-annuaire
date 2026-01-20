package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EtablissementMapperTest {

    private final EtablissementMapper mapper = Mappers.getMapper(EtablissementMapper.class);

    @Test
    void toEntity() {

        // given
        EsrEtablissementDataset dataset = new EsrEtablissementDataset();
        dataset.setUai("1234567A");
        dataset.setNom("Lorem Ipsum");
        dataset.setDateOuverture("");

        // when
        EtablissementEntity entity = mapper.toEntity(dataset);

        // then
        assertThat(entity).isNotNull();
        assertEquals("Lorem Ipsum", entity.getNom());

    }

    @Test
    void toLangueCategorieTest() {
        assertEquals(Langue.Categorie.EU, mapper.toLangueCategorie(OptionEtablissement.SECTION_EUROPEENNE));
        assertEquals(Langue.Categorie.SI, mapper.toLangueCategorie(OptionEtablissement.SECTION_INTERNATIONALE));
        assertEquals(Langue.Categorie.BI, mapper.toLangueCategorie(OptionEtablissement.SECTION_BILINGUE));
        assertEquals(Langue.Categorie.LO, mapper.toLangueCategorie(OptionEtablissement.SECTION_ORIENTALE));
        assertNull(mapper.toLangueCategorie(OptionEtablissement.BFI));
    }

    @Test
    void toSportCategorieTest() {
        assertEquals(Sport.Categorie.SE, mapper.toSportCategorie(OptionEtablissement.SPORT_ETUDES));
        assertEquals(Sport.Categorie.SS, mapper.toSportCategorie(OptionEtablissement.SECTION_SPORT));
        assertNull(mapper.toSportCategorie(OptionEtablissement.EUROSCOL));
    }

    @Test
    void toSportDTOTest() {
        // Partie Sport
        SportDataset sportDataset = new SportDataset();
        sportDataset.setNomSport("boxe");
        sportDataset.setUai("1234567A");
        var out = mapper.toSportDTO(sportDataset, Sport.Categorie.SE);
        assertNotNull(out);

        // Partie Dispositif
        OnisepDispositifDataset onisepDispositifDataset = new OnisepDispositifDataset();
        onisepDispositifDataset.setNom("classe sport-études en collège");
        onisepDispositifDataset.setEnseignement("boxe,bmx");

        var output = mapper.toSportDTO(onisepDispositifDataset, OptionEtablissement.SECTION_SPORT);
        assertNotNull(output);
        assertEquals(2, output.size());
    }

    @Test
    void toOptionDTOTest() {
        OnisepDispositifDataset onisepDispositifDataset = new OnisepDispositifDataset();
        onisepDispositifDataset.setNom("dispositif relais");
        var output = mapper.toOptionDTO(onisepDispositifDataset);
        assertNotNull(output);
        assertEquals(OptionEtablissement.RELAIS, output.option());
    }

    @Test
    void toLangueDTOTest() {

    }

    @Test
    void toJPODTOTest() {

        MasaJpoDataset dataset = new MasaJpoDataset();
        dataset.setMasaId("00100233");

        dataset.setDateDebut("2026-01-30");
        dataset.setDateFin("2026-01-30");
        dataset.setCommentaire("de 17h30 à 20h");

        JPODataset dto = mapper.toJPODTO(dataset);
        assertNotNull(dto);
        assertNotNull(dto.getUai());
        assertNotNull(dto.getDateDebut());
        assertNotNull(dto.getDateFin());


    }
}