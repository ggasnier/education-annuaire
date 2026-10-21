package com.guillaumegasnier.education.web.utils;

import java.util.UUID;

/**
 * Utility class for generating unique error IDs for tracking and logging purposes
 */
public class ErrorIdGenerator {

    private ErrorIdGenerator() {
        // Utility class
    }

    /**
     * Generate a unique error ID
     * Format: ERR-{UUID}
     *
     * @return unique error ID
     */
    public static String generateErrorId() {
        return "ERR-" + UUID.randomUUID().toString();
    }
}

