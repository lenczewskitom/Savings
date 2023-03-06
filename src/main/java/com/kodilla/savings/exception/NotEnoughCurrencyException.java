package com.kodilla.savings.exception;

public class NotEnoughCurrencyException extends Exception{

    public NotEnoughCurrencyException() {
        super("Not enough currency on your account");
    }

}
