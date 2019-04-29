package com.globati.resources;

import com.globati.api.PasswordAttempt;
import com.globati.dynamodb.DynamoProperty;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.dynamodb.DynamoPropertyService;
import com.globati.third_party_api.AWSCredentials;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by daniel on 1/17/17.
 *
 * Refactoring:
 * ok
 */

@Component
@Path("authentication")
public class AuthenticationResource {

    @Context
    private HttpServletResponse servletResponse;

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationResource.class);

    @Autowired
    DynamoPropertyService propertyService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authentication(PasswordAttempt pa){
        LOGGER.debug("authentication()");
        try {
            DynamoProperty propertyAndItems = propertyService.authenticate(pa.getUserName(), pa.getPassword());
             return Response.ok(propertyAndItems)
                     .header("Access-Control-Expose-Headers", "token")
                     .header("token", propertyAndItems.getApiToken())
                     .build();
        }catch(Exception e){
            e.printStackTrace();
            throw new WebException("Password or username did not match", Response.Status.UNAUTHORIZED);
        }
    }

    @GET
    @GlobatiAuthentication
    @Path("aws-credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAwsCredentials(){
        try{
            AWSCredentials aws = new AWSCredentials();
            return Response.ok(aws).build();
        }catch(Exception e){
            throw e;
        }
    }
}
