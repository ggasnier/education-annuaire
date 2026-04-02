package com.guillaumegasnier.education.web.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtre qui protège tous les endpoints /api/** non-GET par une clef d'API.
 * La clef doit être transmise dans le header HTTP {@code X-API-Key}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    public static final String API_KEY_HEADER = "X-API-Key";

    private final AppProperties appProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        // On ne filtre que les requêtes non-GET sur /api/**
        return !path.startsWith("/api/") || HttpMethod.GET.matches(method);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String providedKey = request.getHeader(API_KEY_HEADER);
        String expectedKey = appProperties.getApiKey();

        if (!StringUtils.hasText(expectedKey)) {
            log.warn("Aucune clef d'API configurée (app.properties.api-key) — accès refusé par défaut.");
            sendUnauthorized(response, "Clef d'API non configurée sur le serveur.");
            return;
        }

        if (!expectedKey.equals(providedKey)) {
            log.warn("Tentative d'accès refusée sur {} {} — clef invalide ou absente.", request.getMethod(), request.getRequestURI());
            sendUnauthorized(response, "Clef d'API invalide ou absente. Fournissez le header X-API-Key.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}



