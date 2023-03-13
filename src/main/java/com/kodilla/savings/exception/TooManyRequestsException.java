package com.kodilla.savings.exception;

public class TooManyRequestsException extends Exception{

    public TooManyRequestsException(String message) {
        super(message);
    }
}
