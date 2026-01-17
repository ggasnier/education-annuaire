package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.shell.datasets.etablissements.EsrEtablissementDataset;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.guillaumegasnier.education.shell.mappers.EtablissementMapper.toLangueCategorie;
import static com.guillaumegasnier.education.shell.mappers.EtablissementMapper.toSportCategorie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        // Champs ignorés doivent rester null
        //assertThat(entity.getContacts()).isNull();
        //assertThat(entity.getNature()).isNull();
        //assertThat(entity.getContrat()).isNull();
        //assertThat(entity.getCommune()).isNull();
        //assertThat(entity.getSources()).isNull();
    }

    @Test
    void toLangueCategorieTest() {
        assertEquals(Langue.Categorie.EU, toLangueCategorie(OptionEtablissement.SECTION_EUROPEENNE));
        assertEquals(Langue.Categorie.SI, toLangueCategorie(OptionEtablissement.SECTION_INTERNATIONALE));
        assertEquals(Langue.Categorie.BI, toLangueCategorie(OptionEtablissement.SECTION_BILINGUE));
        assertEquals(Langue.Categorie.LO, toLangueCategorie(OptionEtablissement.SECTION_ORIENTALE));
        assertNull(toLangueCategorie(OptionEtablissement.BFI));
    }

    @Test
    void toSportCategorieTest() {
        assertEquals(Sport.Categorie.SE, toSportCategorie(OptionEtablissement.SPORT_ETUDES));
        assertEquals(Sport.Categorie.SS, toSportCategorie(OptionEtablissement.SECTION_SPORT));
        assertNull(toSportCategorie(OptionEtablissement.EUROSCOL));
    }
}