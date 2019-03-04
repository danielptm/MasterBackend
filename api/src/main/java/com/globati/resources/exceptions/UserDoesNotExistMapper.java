package com.globati.resources.exceptions;

import com.globati.service.mysql.exceptions.UserDoesNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by daniel on 1/19/17.
 *
 * I do not think this is working properly.
 *
 */
@Provider
public class UserDoesNotExistMapper implements ExceptionMapper<UserDoesNotExistException> {


    @Override
    public Response toResponse(UserDoesNotExistException exception){
        return Response.status(
                Response.Status.NOT_FOUND).entity("This globati user profile name does not exist.").build();    }

}
