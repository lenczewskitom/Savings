package com.kodilla.savings.exception;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(Class className) {
        super(className.getSimpleName() + " with given id doesn't exists");
    }
}
