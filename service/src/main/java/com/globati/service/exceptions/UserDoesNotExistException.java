package com.globati.service.exceptions;

/**
 * Created by daniel on 1/19/17.
 */
public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String msg){
        super(msg);
    }
}
