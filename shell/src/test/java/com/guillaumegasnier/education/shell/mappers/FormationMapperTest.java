package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FormationMapperTest {

    private final FormationMapper mapper = Mappers.getMapper(FormationMapper.class);

    @Test
    void toFormationDTOTest() {
        FormationType formationType = new FormationType();
        formationType.setNumero("FOR.123_AF.456");
        FormationDTO dto = mapper.toFormationDTO(formationType);
        assertNotNull(dto);
    }
}