package com.guillaumegasnier.education.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Ensures a stable correlation id for each HTTP request/response lifecycle.
 */
@Deprecated
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String HEADER_NAME = "X-Correlation-Id";
    public static final String REQUEST_ATTRIBUTE = "correlationId";
    public static final String MDC_KEY = "correlationId";

    public static String resolveOrCreateCorrelationId(HttpServletRequest request) {
        Object existingAttribute = request.getAttribute(REQUEST_ATTRIBUTE);
        if (existingAttribute instanceof String id && StringUtils.hasText(id)) {
            return id;
        }

        String fromHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.hasText(fromHeader)) {
            request.setAttribute(REQUEST_ATTRIBUTE, fromHeader);
            return fromHeader;
        }

        String generated = "CORR-" + UUID.randomUUID();
        request.setAttribute(REQUEST_ATTRIBUTE, generated);
        return generated;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String correlationId = resolveOrCreateCorrelationId(request);
        request.setAttribute(REQUEST_ATTRIBUTE, correlationId);
        response.setHeader(HEADER_NAME, correlationId);

        MDC.put(MDC_KEY, correlationId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }
}


