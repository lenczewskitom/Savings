package com.kodilla.savings.exception;

public class NotEnoughCryptoException extends Exception{

    public NotEnoughCryptoException() {
        super("Not enough cryptocurrency on your account");
    }
}
