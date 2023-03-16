package net.socketconnection.jva.exceptions;

public class InvalidRiotIdentificationException extends Exception {
    
    private final String errorMessage;
    
    public InvalidRiotIdentificationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
