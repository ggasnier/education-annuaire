package com.guillaumegasnier.education.web.utils;

import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * Builds a stable error code for grouping recurring incidents.
 */
public final class ErrorCodeResolver {

    private ErrorCodeResolver() {
    }

    public static String resolve(Throwable throwable, HttpStatus status) {
        if (status == HttpStatus.BAD_REQUEST) {
            return "ERR-400-VALIDATION";
        }
        if (status == HttpStatus.CONFLICT) {
            return "ERR-409-CONFLICT";
        }

        String fingerprint = buildFingerprint(throwable, status);
        String hash = shortCrc32Hex(fingerprint);
        return "ERR-" + status.value() + "-" + hash;
    }

    private static String buildFingerprint(Throwable throwable, HttpStatus status) {
        String exceptionName = throwable != null ? throwable.getClass().getName() : "UnknownException";
        String topFrame = "NoStackTrace";
        if (throwable != null && throwable.getStackTrace() != null && throwable.getStackTrace().length > 0) {
            StackTraceElement first = throwable.getStackTrace()[0];
            topFrame = first.getClassName() + "#" + first.getMethodName() + ":" + first.getLineNumber();
        }
        return status.value() + "|" + exceptionName + "|" + topFrame;
    }

    private static String shortCrc32Hex(String input) {
        CRC32 crc32 = new CRC32();
        crc32.update(input.getBytes(StandardCharsets.UTF_8));
        String hex = Long.toHexString(crc32.getValue()).toUpperCase();
        return String.format("%8s", hex).replace(' ', '0');
    }
}

