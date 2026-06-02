package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Getter
public class ContactEtablissementDataset {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final Contact contact;
    private final String valeur;

    private ContactEtablissementDataset(Contact contact, String valeur) {
        this.contact = contact;
        this.valeur = valeur;
    }

    public static Optional<ContactEtablissementDataset> of(@NonNull Contact contact, @NonNull String valeur) {
        return switch (contact) {
            case TEL -> {
                String normalized = valeur.replaceAll("\\D", "");
                log.info(normalized);
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
            default -> {
                log.warn("Type de contact non géré : {}", contact);
                yield Optional.empty();
            }
        };
    }

    private static boolean isValidPhoneNumber(String normalized) {
        return normalized.length() == 10;
    }

    private static boolean isValidEmail(String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }
}
