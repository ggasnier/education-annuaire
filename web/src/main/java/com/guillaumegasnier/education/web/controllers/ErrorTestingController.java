package com.guillaumegasnier.education.web.controllers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Development controller for testing error pages.
 * 
 * This controller is only enabled in development/local profiles.
 * It provides endpoints to manually test the error handling system.
 * 
 * Usage (in development only):
 * - http://localhost:8080/dev/error/404 -> Shows 404 error page
 * - http://localhost:8080/dev/error/500 -> Shows 500 error page
 * - http://localhost:8080/dev/error/generic -> Shows generic error page
 */
@Controller
@RequestMapping("/dev/error")
@ConditionalOnProperty(name = "app.dev.error-testing", havingValue = "true", matchIfMissing = true)
public class ErrorTestingController {

    @GetMapping("/404")
    public String test404() {
        throw new RuntimeException("404 Test Error");
    }

    @GetMapping("/500")
    public String test500() {
        throw new RuntimeException("500 Test Error");
    }

    @GetMapping("/generic")
    public String testGeneric() {
        throw new RuntimeException("Generic Test Error");
    }
}

