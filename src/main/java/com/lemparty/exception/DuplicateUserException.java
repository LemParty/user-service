package com.lemparty.exception;

public class DuplicateUserException extends Exception {

    public DuplicateUserException(String email){
        super("User Exists Already for Email: "+email);
    }
}
