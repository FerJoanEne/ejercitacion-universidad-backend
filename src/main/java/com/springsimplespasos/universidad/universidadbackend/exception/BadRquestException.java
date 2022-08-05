package com.springsimplespasos.universidad.universidadbackend.exception;

public class BadRquestException extends RuntimeException {

    public BadRquestException(String message){
        super(message);
    }
}
