package com.globati.resources;

import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.PropertyInfoService;
import com.globati.service.PropertyService;
import com.globati.service_beans.guest.PropertyAndItems;
import com.globati.third_party_api.AWSCredentials;
import com.globati.request.PasswordAttempt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
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

    private static final Logger log = LogManager.getLogger(AuthenticationResource.class);

    @Autowired
    PropertyService propertyService;

    @Autowired
    PropertyInfoService propertyInfoService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authentication(PasswordAttempt pa){
        log.debug("authentication()");
        try {
            PropertyAndItems propertyAndItems = propertyService.authenticateReceptionist(pa.getUsername(), pa.getPassword());
            log.info("PROPERTY AND ITEMS" + propertyAndItems.getProperty());
             return Response.ok(propertyAndItems).build();
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
