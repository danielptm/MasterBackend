package com.globati.resources.exceptions;

import com.globati.exceptions.IllegalUserNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalIUsernameExceptionMapper implements ExceptionMapper<IllegalUserNameException>{

    @Override
    public Response toResponse(IllegalUserNameException exception) {
        return Response.status(
                Response.Status.EXPECTATION_FAILED).entity("This user name is not available for use.").build();
    }

}
