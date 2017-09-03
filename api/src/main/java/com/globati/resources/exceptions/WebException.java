package com.globati.resources.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by daniel on 12/24/16.
 */
public class WebException extends WebApplicationException {

    public WebException(Response.Status status){
        super(status);
    }

    public WebException(String message, Response.Status status){
        super(message, status);
    }

    public WebException(String message, Response.Status status, Exception e){
        super(message, e);
    }


}
