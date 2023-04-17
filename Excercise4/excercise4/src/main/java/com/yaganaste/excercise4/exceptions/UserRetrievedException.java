package com.yaganaste.excercise4.exceptions;

public class UserRetrievedException extends RuntimeException {
    private String message;

    public UserRetrievedException(String message) {
        super(message);
        this.message = message;
    }
}
