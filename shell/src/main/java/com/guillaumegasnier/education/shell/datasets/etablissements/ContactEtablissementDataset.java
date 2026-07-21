package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
public record ContactEtablissementDataset(Contact contact, String valeur) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public static Optional<ContactEtablissementDataset> of(@NonNull Contact contact, @NonNull String valeur) {
        return switch (contact) {
            case TEL -> {
                String normalized = valeur.replaceAll("\\D", "");
                if (normalized.startsWith("0689"))
                    normalized = normalized.replace("0689", "689");
                if (normalized.startsWith("00687"))
                    normalized = normalized.replace("00687", "687");
                if (normalized.startsWith("4") && normalized.length() == 9)
                    normalized = "0" + normalized;
                if (isValidPhoneNumber(normalized)) {
                    yield Optional.of(new ContactEtablissementDataset(contact, normalized));
                }
                log.warn("Numéro de téléphone invalide ({}), ignoré", valeur);
                yield Optional.empty();
            }
            case EMAIL -> {
                String trimmed = valeur.trim();
                if (isValidEmail(trimmed)) {
                    yield Optional.of(new ContactEtablissementDataset(contact, trimmed));
                }
                log.warn("Adresse e-mail invalide ({}), ignorée", valeur);
                yield Optional.empty();
            }
            case WEB, TWITTER, FACEBOOK, LINKEDIN, YOUTUBE, WIKIPEDIA ->
                    Optional.of(new ContactEtablissementDataset(contact, valeur.trim()));
        };
    }

    private static boolean isValidPhoneNumber(@NonNull String normalized) {
        var length = normalized.length();
        if (length == 10)
            return true;
        if (normalized.startsWith("689") && length == 11) // Polynésie française
            return true;
        return (normalized.startsWith("687") && length == 9); // Nouvelle-Calédonie
    }

    private static boolean isValidEmail(String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }
}
