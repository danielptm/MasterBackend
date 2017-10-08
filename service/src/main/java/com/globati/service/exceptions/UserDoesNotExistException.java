package com.globati.service.exceptions;

/**
 * Created by daniel on 1/19/17.
 *
 * This exception appears to be thrown when a trying to create/update a username when that user exits
 * Therefore the name of this exception appears to be bad. It should be called UserAlreadyExistsException.
 *
 * I think this is attached to an exception mapper, so make sure when refactoring that it is done there too.
 *
 */
public class UserDoesNotExistException extends Exception{

    public UserDoesNotExistException(String msg){
        super(msg);
    }

    public UserDoesNotExistException(String msg, Exception e){
        super(msg, e);
    }
}
