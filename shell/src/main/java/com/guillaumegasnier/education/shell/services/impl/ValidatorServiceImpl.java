package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.shell.services.ValidatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    @Override
    public EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity) {
        Set<ConstraintViolation<EtablissementEntity>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return entity;
        }

        for (ConstraintViolation<EtablissementEntity> v : violations) {
            log.warn("Validation failed on {}.{}: {} ({})",
                    entity.getClass().getSimpleName(),
                    v.getPropertyPath(),
                    v.getMessage(),
                    v.getInvalidValue());
        }

        return null;
    }
}
