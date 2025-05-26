package com.guillaumegasnier.education.annuaire.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SiretValidator implements ConstraintValidator<ValidSiret, String> {

    @Override
    public boolean isValid(String siret, ConstraintValidatorContext context) {
        if (siret == null || siret.isEmpty()) {
            return true; // Champ optionnel
        }

        if (!siret.matches("\\d{14}")) {
            return false;
        }

        return isValidLuhn(siret);
    }

    private boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}

