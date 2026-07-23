package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.services.ValidatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {

    private final Validator validator;

    @Override
    public <T> Optional<T> validate(@NonNull T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return Optional.of(entity);
        }

        for (ConstraintViolation<T> v : violations) {
            log.warn("Validation failed on {}.{}: {} ({})",
                    entity.getClass().getSimpleName(),
                    v.getPropertyPath(),
                    v.getMessage(),
                    v.getInvalidValue());
        }

        return Optional.empty();
    }

}
