package com.dev.exception;

public class UsernameExistedException extends Exception{
    public UsernameExistedException(String username) {
        super(String.format("Username %s already exist",username));
    }
}
