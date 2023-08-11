package net.socketconnection.jva.exceptions;

import java.io.IOException;

public class InvalidRequestException extends IOException {

    private final String errorMessage;


    public InvalidRequestException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
