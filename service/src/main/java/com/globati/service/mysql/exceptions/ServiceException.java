package com.globati.service.mysql.exceptions;

/**
 * Created by daniel on 12/21/16.
 */
public class ServiceException extends Exception {

    String cause;

    public ServiceException(String msg, Throwable e){
        super(msg, e);
    }

    public ServiceException(String msg ){
        super(msg);
    }
}
