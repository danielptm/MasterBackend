package com.globati.service.exceptions;

public class UserNameIsNotUniqueException extends Exception{

    public UserNameIsNotUniqueException(String msg, Exception e){
        super(msg, e);
    }

    public UserNameIsNotUniqueException(String msg){
        super(msg);
    }


}
