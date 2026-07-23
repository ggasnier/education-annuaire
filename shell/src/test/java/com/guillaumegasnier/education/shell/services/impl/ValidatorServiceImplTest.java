package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorServiceImplTest {

    @Mock
    Validator validator;

    ValidatorServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ValidatorServiceImpl(validator);
    }

    @Test
    void toEtablissementContactEntityTest() {
        EtablissementContactEntity entity = new EtablissementContactEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementJPOEntityTest() {
        EtablissementJPOEntity entity = new EtablissementJPOEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementSpecialiteEntityTest() {
        EtablissementSpecialiteEntity entity = new EtablissementSpecialiteEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementLangueEntityTest() {
        EtablissementLangueEntity entity = new EtablissementLangueEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementSportEntityTest() {
        EtablissementSportEntity entity = new EtablissementSportEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toOrganismeEntityTest() {
        OrganismeEntity entity = new OrganismeEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toFormationEntityTest() {
        FormationEntity entity = new FormationEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toActionFormationEntityTest() {
        ActionFormationEntity entity = new ActionFormationEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementMasaEntityTest() {
        EtablissementMasaEntity entity = new EtablissementMasaEntity();
        var result = service.validate(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementOptionEntityTestInvalid() {
        EtablissementOptionEntity opt = new EtablissementOptionEntity();
        ConstraintViolation violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("someField");
        when(violation.getPropertyPath()).thenReturn(path);
        when(validator.validate(opt)).thenReturn(Collections.singleton(violation));

        var result = service.validate(opt);
        assertNotNull(result);
    }

}
