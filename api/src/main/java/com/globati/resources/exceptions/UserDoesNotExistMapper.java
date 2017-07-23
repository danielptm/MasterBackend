package com.globati.resources.exceptions;

import com.globati.service.exceptions.UserDoesNotExistException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by daniel on 1/19/17.
 */
@Provider
public class UserDoesNotExistMapper {

    public Response toResponse(UserDoesNotExistException exception) {
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
}
