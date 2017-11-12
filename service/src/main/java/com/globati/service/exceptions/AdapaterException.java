package com.globati.service.exceptions;

public class AdapaterException extends Exception{

    String cause;

    public AdapaterException(String msg, Throwable e){
        super(msg, e);
    }

    public AdapaterException(String msg ){
        super(msg);
    }
}
