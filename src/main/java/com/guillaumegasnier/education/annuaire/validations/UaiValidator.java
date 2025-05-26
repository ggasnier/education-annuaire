package com.guillaumegasnier.education.annuaire.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.NonNull;

public class UaiValidator implements ConstraintValidator<ValidUai, String> {

    private static final char[] UAI_ALPHABET = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    @Override
    public void initialize(ValidUai constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uai, ConstraintValidatorContext constraintValidatorContext) {
        if (uai == null || uai.isEmpty()) {
            return true; // Champ optionnel
        }

        if (!uai.matches("\\d{7}[A-Z]")) {
            return false;
        }

        return isValidUai(uai);
    }

    private boolean isValidUai(@NonNull String uai) {
        String digitsPart = uai.substring(0, 7);
        char keyLetter = uai.charAt(7);
        int number = Integer.parseInt(digitsPart);
        int remainder = number % 23;
        char expectedKey = UAI_ALPHABET[remainder];
        return keyLetter == expectedKey;
    }
}
