package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.utils.ErrorIdGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom Error Controller to handle 404, 500 and other HTTP errors
 */
@Slf4j
public class CustomErrorController {

    private static final String ERROR_VIEW = "error/error";
    private static final String ERROR_ID_ATTR = "errorId";
    private static final String STATUS_ATTR = "status";
    private static final String MESSAGE_ATTR = "message";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorId = ErrorIdGenerator.generateErrorId();
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (statusCode == null) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        String message = getMessage(statusCode);

        log.error("[{}] HTTP Error {}: {} - URI: {}", 
                errorId, statusCode, message, requestUri, throwable);

        model.addAttribute(ERROR_ID_ATTR, errorId);
        model.addAttribute(STATUS_ATTR, statusCode);
        model.addAttribute(MESSAGE_ATTR, message);
        model.addAttribute("requestUri", requestUri);

        // Route to specific error template based on status code
        return routeToErrorTemplate(statusCode);
    }

    /**
     * Get error message based on HTTP status code
     */
    private String getMessage(int statusCode) {
        return switch (statusCode) {
            case 400 -> "Requête invalide";
            case 401 -> "Non authentifié";
            case 403 -> "Accès refusé";
            case 404 -> "Page non trouvée";
            case 405 -> "Méthode non autorisée";
            case 409 -> "Conflit";
            case 500 -> "Erreur serveur";
            case 502 -> "Mauvaise passerelle";
            case 503 -> "Service indisponible";
            default -> "Une erreur est survenue";
        };
    }

    /**
     * Route to specific error template or default error template
     */
    private String routeToErrorTemplate(int statusCode) {
        return switch (statusCode) {
            case 404 -> "error/404";
            case 500 -> "error/500";
            default -> ERROR_VIEW;
        };
    }
}

