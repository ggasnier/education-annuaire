package com.guillaumegasnier.education.shell.utils;

import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShellUtil {

    private static final Pattern ENDING_PATTERN = Pattern.compile("([A-Z]+)\\.(\\d+)$");
    private static final Pattern RNCP_PATTERN =
            Pattern.compile("https://www\\.francecompetences\\.fr/recherche/rncp/(\\d+)/?$", Pattern.CASE_INSENSITIVE);

    @Nullable
    public static Integer extractOnisepId(String url) {
        if (url == null) return null;

        Matcher matcher = ENDING_PATTERN.matcher(url);
        return matcher.find() ? Integer.parseInt(matcher.group(2)) : null;
    }

    @Nullable
    public static String extractRncpCode(String url) {
        if (url == null) return null;

        Matcher matcher = RNCP_PATTERN.matcher(url);
        return matcher.find() ? "RNCP" + matcher.group(1) : null;
    }
}
