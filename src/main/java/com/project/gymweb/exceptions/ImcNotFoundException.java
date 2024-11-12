package com.project.gymweb.exceptions;

public class ImcNotFoundException extends RuntimeException {
    public ImcNotFoundException(String message) {
        super(message);
    }

    public ImcNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
