package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
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
        Set<ConstraintViolation<EtablissementEntity>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return entity;
        }

        for (ConstraintViolation<EtablissementEntity> v : violations) {
            if (v.getPropertyPath().toString().contains("siret") && violations.size() == 1) {
                log.warn("UAI {} avec siret invalide {}", entity.getUai(), entity.getSiret());
                entity.setSiret(null);
                return entity;
            } else {
                log.error("Validation failed on {}.{}: {} ({})",
                        entity.getClass().getSimpleName(),
                        v.getPropertyPath(),
                        v.getMessage(),
                        v.getInvalidValue());
            }
        }

        return null;
    }

    @Override
    public EtablissementOptionEntity toValidEntity(@NonNull EtablissementOptionEntity entity) {
        return toValidEntity(entity, EtablissementOptionEntity.class);
    }

    @Override
    public EtablissementContactEntity toValidEntity(@NonNull EtablissementContactEntity entity) {
        return toValidEntity(entity, EtablissementContactEntity.class);
    }

    @Override
    public EtablissementJPOEntity toValidEntity(@NonNull EtablissementJPOEntity entity) {
        return toValidEntity(entity, EtablissementJPOEntity.class);
    }

    @Override
    public EtablissementSpecialiteEntity toValidEntity(@NonNull EtablissementSpecialiteEntity entity) {
        return toValidEntity(entity, EtablissementSpecialiteEntity.class);
    }

    @Override
    public EtablissementLangueEntity toValidEntity(@NonNull EtablissementLangueEntity entity) {
        return toValidEntity(entity, EtablissementLangueEntity.class);
    }

    @Override
    public EtablissementSportEntity toValidEntity(@NonNull EtablissementSportEntity entity) {
        return toValidEntity(entity, EtablissementSportEntity.class);
    }

    @Override
    public OrganismeEntity toValidEntity(@NonNull OrganismeEntity entity) {
        return toValidEntity(entity, OrganismeEntity.class);
    }

    @Override
    public FormationEntity toValidEntity(@NonNull FormationEntity entity) {
        return toValidEntity(entity, FormationEntity.class);
    }

    @Override
    public ActionFormationEntity toValidEntity(@NonNull ActionFormationEntity entity) {
        return toValidEntity(entity, ActionFormationEntity.class);
    }

    @Override
    public EtablissementMasaEntity toValidEntity(@NonNull EtablissementMasaEntity entity) {
        return toValidEntity(entity, EtablissementMasaEntity.class);
    }
}
