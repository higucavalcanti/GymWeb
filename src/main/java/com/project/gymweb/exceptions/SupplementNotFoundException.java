package com.project.gymweb.exceptions;

public class SupplementNotFoundException extends RuntimeException {
    public SupplementNotFoundException(String message) {
        super(message);
    }

    public SupplementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
