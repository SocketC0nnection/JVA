package net.socketconnection.jva.exceptions;

import java.io.IOException;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

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
