package com.dozee.userdetails.exception;

public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
