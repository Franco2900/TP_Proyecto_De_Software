package com.UNLaLibrary.TP_Proyecto_De_Software.exceptions;

public class EmailAlreadyExistException extends Exception {
    public EmailAlreadyExistException() {
        super();
    }


    public EmailAlreadyExistException(String message) {
        super(message);
    }


    public EmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}