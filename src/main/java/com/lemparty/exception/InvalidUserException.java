package com.lemparty.exception;

public class InvalidUserException extends Exception {

    public InvalidUserException(String email){
        super("No User Exists For Email: "+email);
    }
}
