package com.dev.exception;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect Password");
    }
}
