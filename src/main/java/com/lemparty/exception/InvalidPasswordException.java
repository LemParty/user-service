package com.lemparty.exception;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String email){
        super("Invalid Email or Password combination");
    }
}
