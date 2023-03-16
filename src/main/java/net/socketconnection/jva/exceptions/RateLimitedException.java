package net.socketconnection.jva.exceptions;

import java.io.IOException;

public class RateLimitedException extends IOException {

    private final String errorMessage;

    public RateLimitedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
