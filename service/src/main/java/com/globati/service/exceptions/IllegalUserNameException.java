package com.globati.service.exceptions;

public class IllegalUserNameException extends Exception {

    public IllegalUserNameException(Exception e){
        super(e);
    }

    public IllegalUserNameException(String msg){
        super(msg);
    }

}
