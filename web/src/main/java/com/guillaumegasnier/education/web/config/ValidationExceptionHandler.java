package com.guillaumegasnier.education.web.config;

import com.guillaumegasnier.education.web.dto.ApiError;
import com.guillaumegasnier.education.web.exceptions.EtablissementAlreadyExistsException;
import com.guillaumegasnier.education.web.utils.ErrorIdGenerator;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Legacy API validation exception handler - kept for reference.
 * Replaced by GlobalErrorHandler which handles both HTML and JSON responses.
 * <p>
 * This class is kept for backwards compatibility but should not be used.
 * All error handling is now delegated to GlobalErrorHandler.
 */
@Deprecated
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Handle validation errors for method arguments
     *
     * @param ex the MethodArgumentNotValidException
     * @return map of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorId = ErrorIdGenerator.generateErrorId();
        log.error("[{}] Method Argument Not Valid Exception, ", errorId, ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorId = ErrorIdGenerator.generateErrorId();
        log.error("[{}] Constraint Violation Exception, ", errorId, ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setErrorId(errorId);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EtablissementAlreadyExistsException.class)
    protected ResponseEntity<ApiError> handleEtablissementAlreadyExistsException(EtablissementAlreadyExistsException ex) {
        String errorId = ErrorIdGenerator.generateErrorId();
        log.warn("[{}] Tentative de création d'un établissement déjà existant : UAI={}", errorId, ex.getUai());
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setErrorId(errorId);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
