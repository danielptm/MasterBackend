package com.globati.resources;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.mysql.dbmodel.Property;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.dynamodb.DynamoPropertyService;
import com.globati.service.JwtService;
import com.globati.exceptions.IllegalUserNameException;
import com.globati.exceptions.ServiceException;
import com.globati.exceptions.UserDoesNotExistException;
import com.globati.HelpObjects.ChangePassword;
import com.globati.exceptions.UserNameIsNotUniqueException;
import com.globati.api.ChangePasswordWithToken;
import com.globati.api.RequestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;


/**
 * Created by daniel on 12/13/16.
 *
 * Refactoring:
 * ok
 *
 */

@Component
@Path("property")
public class PropertyResource{

    private static final Logger LOGGER = LogManager.getLogger(PropertyResource.class);



    @Autowired
    DynamoPropertyService propertyService;

    @Autowired
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RequestProperty requestProperty) {
            DynamoProperty property = propertyService.createDynamoProperty(requestProperty);
            return Response.ok(property).build();

    }

    /**
     *
     * This is called after the authenticationResource is accessed, because that just returned credentials.
     * This logs the user in and gets their data.
     *
     * @param email
     * @return
     */
    @POST
    @Path("login/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("email") String email){
        try{
            DynamoProperty dynamoProperty = propertyService.getDynamoPropertyById(email);
            return Response.ok(dynamoProperty).build();
        }catch(Exception e){
            e.printStackTrace();
            throw new WebException("Could not retrieve user by username and password", Response.Status.BAD_REQUEST);
        }
    }

    /**
     *
     * Try and take this away. If I use an RequestProperty object on the client site, update all its fields, and
     * then just do the same here, then it should work to take away the check with null checks.
     *
     *
     * @return
     * @throws ServiceException
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @GlobatiAuthentication
    public Response update(RequestProperty requestProperty) {
        DynamoProperty updatedProperty = propertyService.updateDynamoProperty(requestProperty);
        return Response.ok(updatedProperty).build();
    }


    @POST
    @Path("changepassword")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(ChangePassword passwords){
        try{
            if(propertyService.changePassword(passwords.getEmail(), passwords.getOldPassword(), passwords.getNewPassword())){
                return Response.ok("Password changed").build();
            }
            else{
                throw new Exception();
            }
        }catch(Exception e){
            LOGGER.error("");
            throw new WebException("Could not change password", Response.Status.FORBIDDEN);
        }
    }

    @POST
    @Path("changepasswordwithtoken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePasswordWithToken(ChangePasswordWithToken changePasswordWithToken){
        try{
            propertyService.changePasswordWithToken(
                    changePasswordWithToken.getEmail(),
                    changePasswordWithToken.getToken(),
                    changePasswordWithToken.getPassword(),
                    changePasswordWithToken.getOldPassword());
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not change the password with token", Response.Status.CONFLICT);
        }
    }

}
