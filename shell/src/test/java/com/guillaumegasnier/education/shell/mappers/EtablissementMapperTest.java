package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.shell.datasets.etablissements.EsrEtablissementDataset;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}