package com.msvc.calificacion.exceptions;

public class ResourceNotFundException extends RuntimeException{
    public ResourceNotFundException(){
        super("Recurso no encontrado");
    }
    public ResourceNotFundException(String message){
        super(message);
    }
}
