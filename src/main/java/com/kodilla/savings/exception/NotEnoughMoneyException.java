package com.kodilla.savings.exception;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException() {
        super("Not enough money on your account");
    }
}
