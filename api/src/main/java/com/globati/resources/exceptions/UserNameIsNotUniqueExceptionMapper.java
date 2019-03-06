package com.globati.resources.exceptions;

import com.globati.exceptions.UserNameIsNotUniqueException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNameIsNotUniqueExceptionMapper implements ExceptionMapper<UserNameIsNotUniqueException> {

    @Override
    public Response toResponse(UserNameIsNotUniqueException exception) {
        return Response.status(Response.Status.EXPECTATION_FAILED)
                .entity("Sombody is using this user name already, please select a unique username.").build();
    }

}
