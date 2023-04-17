package com.yaganste.web.Login.exceptions;

public class UserCredentialException extends RuntimeException {
    private String message;

    public UserCredentialException(String message) {
        super(message);
        this.message = message;
    }
}
