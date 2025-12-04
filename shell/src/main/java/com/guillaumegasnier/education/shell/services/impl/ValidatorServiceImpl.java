package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.etablissements.OrganismeEntity;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class ValidatorServiceImpl implements ValidatorService {

    private final Validator validator;

    @Autowired
    public ValidatorServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Nullable
    private <T> T toValidEntity(@NonNull T entity, @NonNull Class<T> clazz) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return entity;
        }

        for (ConstraintViolation<T> v : violations) {
            log.warn("Validation failed on {}.{}: {} ({})",
                    entity.getClass().getSimpleName(),
                    v.getPropertyPath(),
                    v.getMessage(),
                    v.getInvalidValue());
        }

        return null;
    }

    @Override
    public EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity) {
        return toValidEntity(entity, EtablissementEntity.class);
    }

    @Override
    public EtablissementOptionEntity toValidEntity(@NonNull EtablissementOptionEntity entity) {
        return toValidEntity(entity, EtablissementOptionEntity.class);
    }

    @Override
    public EtablissementContactEntity toValidEntity(@NonNull EtablissementContactEntity entity) {
        return toValidEntity(entity, EtablissementContactEntity.class);
    }

//    @Override
//    public IndicePositionSocialeEntity toValidEntity(@NonNull IndicePositionSocialeEntity entity) {
//        return toValidEntity(entity, IndicePositionSocialeEntity.class);
//    }

    /*@Override
    public SectionInternationaleEntity toValidEntity(@NonNull SectionInternationaleEntity entity) {
        return toValidEntity(entity, SectionInternationaleEntity.class);
    }*/

    @Override
    public EtablissementSpecialiteEntity toValidEntity(@NonNull EtablissementSpecialiteEntity entity) {
        return toValidEntity(entity, EtablissementSpecialiteEntity.class);
    }

    @Override
    public EtablissementLangueEntity toValidEntity(@NonNull EtablissementLangueEntity entity) {
        return toValidEntity(entity, EtablissementLangueEntity.class);
    }

    /*@Override
    public SportEtudeEntity toValidEntity(@NonNull SportEtudeEntity entity) {
        return toValidEntity(entity, SportEtudeEntity.class);
    }

    @Override
    public SectionSportiveEntity toValidEntity(@NonNull SectionSportiveEntity entity) {
        return toValidEntity(entity, SectionSportiveEntity.class);
    }*/

    @Override
    public EtablissementSportEntity toValidEntity(@NonNull EtablissementSportEntity entity) {
        return toValidEntity(entity, EtablissementSportEntity.class);
    }

    @Override
    public OrganismeEntity toValidEntity(@NonNull OrganismeEntity entity) {
        return toValidEntity(entity, OrganismeEntity.class);
    }
}
