package com.guillaumegasnier.education.shell.utils;

import com.guillaumegasnier.education.shell.datasets.etablissements.JPODataset;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShellUtil {

    private static final Pattern ENDING_PATTERN = Pattern.compile("([A-Z]+)\\.(\\d+)$");
    private static final Pattern RNCP_PATTERN = Pattern
            .compile("https://www\\.francecompetences\\.fr/recherche/rncp/(\\d+)/?$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_JPO_1 = Pattern.compile(
            "le\\s+(\\d{2}/\\d{2}/\\d{4})\\s+de\\s+(\\d{2}h\\d{2})\\s+à\\s+(\\d{2}h\\d{2})\\s*(.*?)\\s*$", Pattern.CANON_EQ);
    private static final Pattern PATTERN_JPO_2 = Pattern.compile(
            "le\\s+(\\d{2}/\\d{2}/\\d{4})\\s*(.*?)\\s*$");
    private static final Pattern PATTERN_JPO_3 = Pattern.compile(
            "du\\s+(\\d{2}/\\d{2}/\\d{4})\\s+au\\s+(\\d{2}/\\d{2}/\\d{4})\\s+de\\s+(\\d{2}h\\d{2})\\s+à\\s+(\\d{2}h\\d{2})\\s*(.*?)\\s*$", Pattern.CANON_EQ);
    private static final Pattern PATTERN_JPO_4 = Pattern.compile(
            "du\\s+(\\d{2}/\\d{2}/\\d{4})\\s+au\\s+(\\d{2}/\\d{2}/\\d{4})\\s*(.*?)\\s*$");

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH'h'mm");

    @Nullable
    public static Integer extractOnisepId(String url) {
        if (url == null)
            return null;

        Matcher matcher = ENDING_PATTERN.matcher(url);
        return matcher.find() ? Integer.parseInt(matcher.group(2)) : null;
    }

    @Nullable
    public static String extractRncpCode(String url) {
        if (url == null)
            return null;

        Matcher matcher = RNCP_PATTERN.matcher(url);
        return matcher.find() ? "RNCP" + matcher.group(1) : null;
    }

    @Nullable
    public static Double formatDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    public static JPODataset formatJPODataset(String uai, String input) {
        if (input == null || input.isBlank())
            return null;

        Matcher matcher1 = PATTERN_JPO_1.matcher(input);
        Matcher matcher2 = PATTERN_JPO_2.matcher(input);
        Matcher matcher3 = PATTERN_JPO_3.matcher(input);
        Matcher matcher4 = PATTERN_JPO_4.matcher(input);

        JPODataset jpo = new JPODataset();
        jpo.setUai(uai);

        if (matcher3.find()) {
            // Format multi-jours: du DD/MM/YYYY au DD/MM/YYYY de HHhMM à HHhMM
            jpo.setDateDebut(LocalDate.parse(matcher3.group(1), dateFormatter));
            jpo.setDateFin(LocalDate.parse(matcher3.group(2), dateFormatter));
            jpo.setHeureDebut(LocalTime.parse(matcher3.group(3), timeFormatter));
            jpo.setHeureFin(LocalTime.parse(matcher3.group(4), timeFormatter));
            jpo.setCommentaire(parseCommentaire(matcher3.group(5)));
            return jpo;
        } else if (matcher4.find()) {
            // Format multi-jours sans heures: du DD/MM/YYYY au DD/MM/YYYY
            jpo.setDateDebut(LocalDate.parse(matcher4.group(1), dateFormatter));
            jpo.setDateFin(LocalDate.parse(matcher4.group(2), dateFormatter));
            jpo.setCommentaire(parseCommentaire(matcher4.group(3)));
            return jpo;
        } else if (matcher1.find()) {
            // Format classique: le DD/MM/YYYY de HHhMM à HHhMM
            LocalDate date = LocalDate.parse(matcher1.group(1), dateFormatter);
            jpo.setDateDebut(date);
            jpo.setDateFin(date);
            jpo.setHeureDebut(LocalTime.parse(matcher1.group(2), timeFormatter));
            jpo.setHeureFin(LocalTime.parse(matcher1.group(3), timeFormatter));
            jpo.setCommentaire(parseCommentaire(matcher1.group(4)));
            return jpo;
        } else if (matcher2.find()) {
            // Format simple: le DD/MM/YYYY
            LocalDate date = LocalDate.parse(matcher2.group(1), dateFormatter);
            jpo.setDateDebut(date);
            jpo.setDateFin(date);
            jpo.setCommentaire(parseCommentaire(matcher2.group(2)));
            return jpo;
        } else {
            log.warn("Format non pris en charge pour {} JPO:{}", uai, input);
        }

        return null;
    }

    @Nullable
    private static String parseCommentaire(String raw) {
        if (raw == null || raw.trim().isBlank())
            return null;
        String trimmed = raw.trim();
        return trimmed.startsWith(",") ? trimmed.substring(1).trim() : trimmed;
    }

    public static long toNormalizedId(@NonNull String namespace, @NonNull String sourceId) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    (namespace + ":" + sourceId).getBytes(StandardCharsets.UTF_8)
            );
            return ByteBuffer.wrap(hash).getLong() & Long.MAX_VALUE;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String formatAdresse(String adresse) {
        if (adresse == null) return null;
        if (adresse.isBlank()) return null;
        if (adresse.length() > 50) return adresse.substring(0, 50);
        return adresse.trim();
    }
}
