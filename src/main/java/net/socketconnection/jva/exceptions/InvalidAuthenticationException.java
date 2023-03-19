package net.socketconnection.jva.exceptions;

import java.io.IOException;

public class InvalidAuthenticationException extends IOException {

    private final String errorMessage;

    public InvalidAuthenticationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
