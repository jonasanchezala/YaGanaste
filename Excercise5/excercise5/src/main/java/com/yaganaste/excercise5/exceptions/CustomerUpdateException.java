package com.yaganaste.excercise5.exceptions;

public class CustomerUpdateException extends RuntimeException {
    private String message;

    public CustomerUpdateException(String message) {
        super(message);
        this.message = message;
    }
}
