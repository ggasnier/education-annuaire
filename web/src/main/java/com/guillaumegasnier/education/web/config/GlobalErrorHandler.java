package com.guillaumegasnier.education.web.config;

import com.guillaumegasnier.education.web.dto.ApiError;
import com.guillaumegasnier.education.web.exceptions.EtablissementAlreadyExistsException;
import com.guillaumegasnier.education.web.utils.ErrorCodeResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling errors in both HTML pages (Thymeleaf)
 * and REST API responses with unique error IDs for tracking and logging.
 */
@Deprecated
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalErrorHandler {

    private static final String ERROR_VIEW = "error/error";
    private static final String ERROR_ID_ATTR = "errorId";
    private static final String ERROR_CODE_ATTR = "errorCode";
    private static final String STATUS_ATTR = "status";
    private static final String MESSAGE_ATTR = "message";

    /**
     * Handle validation errors for method arguments
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage()));

        String errorId = CorrelationIdFilter.resolveOrCreateCorrelationId(request);
        String errorCode = ErrorCodeResolver.resolve(ex, HttpStatus.BAD_REQUEST);
        log.error("[{}][{}] Method Argument Not Valid Exception: {}", errorCode, errorId, errors, ex);

        if (isApiRequest(request)) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setErrorId(errorId);
            apiError.setErrorCode(errorCode);
            apiError.setMessage("Validation error");
            apiError.addFieldValidationErrors(ex.getBindingResult().getFieldErrors());
            apiError.addGlobalValidationErrors(ex.getBindingResult().getGlobalErrors());
            return ResponseEntity.badRequest().body(apiError);
        }

        return createErrorModelAndView(errorId, HttpStatus.BAD_REQUEST,
                "Erreur de validation", errorCode, request);
    }

    /**
     * Handle constraint violation exceptions
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        String errorId = CorrelationIdFilter.resolveOrCreateCorrelationId(request);
        String errorCode = ErrorCodeResolver.resolve(ex, HttpStatus.BAD_REQUEST);
        log.error("[{}][{}] Constraint Violation Exception: {}", errorCode, errorId, ex.getMessage(), ex);

        if (isApiRequest(request)) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setErrorId(errorId);
            apiError.setErrorCode(errorCode);
            apiError.setMessage("Validation error");
            apiError.addValidationErrors(ex.getConstraintViolations());
            return ResponseEntity.badRequest().body(apiError);
        }

        return createErrorModelAndView(errorId, HttpStatus.BAD_REQUEST,
                "Erreur de validation", errorCode, request);
    }

    /**
     * Handle EtablissementAlreadyExistsException
     */
    @ExceptionHandler(EtablissementAlreadyExistsException.class)
    public Object handleEtablissementAlreadyExistsException(
            EtablissementAlreadyExistsException ex,
            HttpServletRequest request) {

        String errorId = CorrelationIdFilter.resolveOrCreateCorrelationId(request);
        String errorCode = ErrorCodeResolver.resolve(ex, HttpStatus.CONFLICT);
        log.warn("[{}][{}] Tentative de création d'un établissement déjà existant : UAI={}",
                errorCode, errorId, ex.getUai());

        if (isApiRequest(request)) {
            ApiError apiError = new ApiError(HttpStatus.CONFLICT);
            apiError.setErrorId(errorId);
            apiError.setErrorCode(errorCode);
            apiError.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
        }

        return createErrorModelAndView(errorId, HttpStatus.CONFLICT,
                ex.getMessage(), errorCode, request);
    }

    /**
     * Handle generic exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        String errorId = CorrelationIdFilter.resolveOrCreateCorrelationId(request);
        String errorCode = ErrorCodeResolver.resolve(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("[{}][{}] Unexpected error: {}", errorCode, errorId, ex.getMessage(), ex);

        if (isApiRequest(request)) {
            ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
            apiError.setErrorId(errorId);
            apiError.setErrorCode(errorCode);
            if (apiError.getMessage() == null || apiError.getMessage().isEmpty()) {
                apiError.setMessage("Une erreur est survenue");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
        }

        return createErrorModelAndView(errorId, HttpStatus.INTERNAL_SERVER_ERROR,
                "Une erreur est survenue. Notre équipe a été notifiée.", errorCode, request);
    }

    /**
     * Create a ModelAndView for error pages
     */
    private ModelAndView createErrorModelAndView(
            String errorId,
            HttpStatus status,
            String message,
            String errorCode,
            HttpServletRequest request) {

        ModelAndView mav = new ModelAndView(ERROR_VIEW);
        mav.addObject(ERROR_ID_ATTR, errorId);
        mav.addObject(ERROR_CODE_ATTR, errorCode);
        mav.addObject(STATUS_ATTR, status.value());
        mav.addObject(MESSAGE_ATTR, message);
        mav.addObject("requestUri", request.getRequestURI());
        mav.setStatus(status);
        return mav;
    }

    /**
     * Check if the request is for API (JSON response) or HTML
     */
    private boolean isApiRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        String uri = request.getRequestURI();

        // Check if it's an API endpoint (starts with /api) or requests JSON
        return uri.startsWith("/api") ||
                (accept != null && (accept.contains("application/json") ||
                        accept.contains("application/*")));
    }
}

