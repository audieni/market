package com.audieni.market.exceptions;

public class ExistingUserException extends Exception {
    public ExistingUserException() {
        super("User already exists with this email.");
    }
}
