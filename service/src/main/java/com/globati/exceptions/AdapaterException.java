package com.globati.exceptions;

public class AdapaterException extends Exception{

    String cause;

    public AdapaterException(String msg, Throwable e){
        super(msg, e);
    }

    public AdapaterException(String msg ){
        super(msg);
    }
}
