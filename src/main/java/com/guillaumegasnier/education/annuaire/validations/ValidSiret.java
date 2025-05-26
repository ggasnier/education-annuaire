package com.guillaumegasnier.education.annuaire.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SiretValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSiret {
    String message() default "SIRET invalide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
