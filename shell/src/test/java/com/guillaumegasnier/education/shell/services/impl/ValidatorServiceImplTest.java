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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementJPOEntityTest() {
        EtablissementJPOEntity entity = new EtablissementJPOEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementSpecialiteEntityTest() {
        EtablissementSpecialiteEntity entity = new EtablissementSpecialiteEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementLangueEntityTest() {
        EtablissementLangueEntity entity = new EtablissementLangueEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementSportEntityTest() {
        EtablissementSportEntity entity = new EtablissementSportEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toOrganismeEntityTest() {
        OrganismeEntity entity = new OrganismeEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toFormationEntityTest() {
        FormationEntity entity = new FormationEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toActionFormationEntityTest() {
        ActionFormationEntity entity = new ActionFormationEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementMasaEntityTest() {
        EtablissementMasaEntity entity = new EtablissementMasaEntity();
        var result = service.toValidEntity(entity);
        assertNotNull(result);
    }

    @Test
    void toEtablissementOptionEntityTestValid() {
        EtablissementOptionEntity opt = new EtablissementOptionEntity();
        when(validator.validate(opt)).thenReturn(Collections.emptySet());

        var result = service.toValidEntity(opt);
        assertNotNull(result);
        assertSame(opt, result);
    }

    @Test
    void toEtablissementOptionEntityTestInvalid() {
        EtablissementOptionEntity opt = new EtablissementOptionEntity();
        ConstraintViolation violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("someField");
        when(violation.getPropertyPath()).thenReturn(path);
        when(validator.validate(opt)).thenReturn(Collections.singleton(violation));

        var result = service.toValidEntity(opt);
        assertNull(result);
    }

    @Test
    void toEtablissementEntityTest() {
        EtablissementEntity e = new EtablissementEntity();
        e.setUai("U1234567");
        e.setSiret("12345678901234");
        e.setNom("Test");

        ConstraintViolation<EtablissementEntity> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("siret");
        when(violation.getPropertyPath()).thenReturn(path);

        when(validator.validate(e)).thenReturn(Collections.singleton(violation));

        EtablissementEntity result = service.toValidEntity(e);
        assertNotNull(result);
        assertNull(result.getSiret(), "Siret should be nullified when it's the only violation");
        assertEquals("U1234567", result.getUai());
    }

    @Test
    void toEtablissementEntityTest2() {
        EtablissementEntity e = new EtablissementEntity();
        e.setUai("U1234567");
        e.setSiret("12345678901234");
        e.setNom("Test");

        ConstraintViolation<EtablissementEntity> v1 = mock(ConstraintViolation.class);
        Path p1 = mock(Path.class);
        when(p1.toString()).thenReturn("siret");
        when(v1.getPropertyPath()).thenReturn(p1);

        ConstraintViolation<EtablissementEntity> v2 = mock(ConstraintViolation.class);
        Path p2 = mock(Path.class);
        when(p2.toString()).thenReturn("nom");
        when(v2.getPropertyPath()).thenReturn(p2);

        Set<ConstraintViolation<EtablissementEntity>> violations = Set.of(v1, v2);
        when(validator.validate(e)).thenReturn(violations);

        EtablissementEntity result = service.toValidEntity(e);
        assertNull(result);
    }
}
