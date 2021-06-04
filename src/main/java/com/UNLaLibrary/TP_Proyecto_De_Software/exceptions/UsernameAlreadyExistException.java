package com.UNLaLibrary.TP_Proyecto_De_Software.exceptions;

public class UsernameAlreadyExistException extends Exception {
    public UsernameAlreadyExistException() {
        super();
    }


    public UsernameAlreadyExistException(String message) {
        super(message);
    }


    public UsernameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

