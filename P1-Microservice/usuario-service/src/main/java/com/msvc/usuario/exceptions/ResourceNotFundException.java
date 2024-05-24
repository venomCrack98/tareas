package com.msvc.usuario.exceptions;

public class ResourceNotFundException extends RuntimeException{
    public ResourceNotFundException() {
        super("Recurso no encontrado en el servidor");
    }

    public ResourceNotFundException(String message) {
        super(message);
    }
}
