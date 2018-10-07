package com.globati.resources;

import com.globati.dbmodel.Property;
import com.globati.deserialization_beans.request.CreateProperty;
import com.globati.deserialization_beans.request.InterestMail;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.PropertyInfoService;
import com.globati.service.PropertyService;
import com.globati.service.JwtService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.HelpObjects.ChangePassword;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.utildb.SendMail;
import com.globati.deserialization_beans.request.ChangePasswordWithToken;
import com.globati.deserialization_beans.request.UpdateProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.List;
import com.globati.HelpObjects.Email;



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

    private static final Logger log = LogManager.getLogger(PropertyResource.class);

    @Context
    UriInfo uri;

    @Autowired
    PropertyService propertyService;

    @Autowired
    PropertyInfoService propertyInfoService;

    @Autowired
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateProperty createProperty) throws UserNameIsNotUniqueException, ServiceException {
            Property employee = this.propertyService.createProperty(
                    createProperty.getFirstName(),
                    createProperty.getEmail(),
                    createProperty.getUsername(),
                    createProperty.getPassword(),
                    createProperty.getTargetLat(),
                    createProperty.getTargetLong(),
                    createProperty.getImage(),
                    createProperty.getStreet(),
                    createProperty.getCity(),
                    createProperty.getCountry()
            );

            return Response.ok(employee).build();

    }

    /**
     *
     * This is called after the authenticationResource is accessed, because that just returned credentials.
     * This logs the user in and gets their data.
     *
     * @param username
     * @return
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response login(String username){
        try{
            return Response.ok(propertyService.getItemsForProperty(username)).build();
        }catch(Exception e){
            e.printStackTrace();
            throw new WebException("Could not retrieve user by username and password", Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("interest-mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendInterestMail(InterestMail mail) throws Exception {
        SendMail.sendInterestMail("daniel@globati.com", mail.toString());
        SendMail.sendInterestMail("oliver@globati.com", mail.toString());
        SendMail.sendInterestMail("edward@globati.com", mail.toString());
        return Response.ok().build();
    }

    /**
     *
     * Try and take this away. If I use an UpdateProperty object on the client site, update all its fields, and
     * then just do the same here, then it should work to take away the check with null checks.
     *
     *
     * @return
     * @throws ServiceException
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response update(@QueryParam("employeeId") Long employeeId, UpdateProperty updateProperty) throws ServiceException, IOException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        Property employee = null;
        employee = propertyService.getPropertyById(employeeId);

        if(updateProperty.getEmail()!=null){
            employee.setEmail(updateProperty.getEmail());
        }
        if(updateProperty.getUsername()!=null){
            employee.setGlobatiUsername(updateProperty.getUsername());
        }
        if(updateProperty.getPropLong()!=null){
            employee.setPropLong(updateProperty.getPropLong());
        }
        if(updateProperty.getPropLat()!=null){
            employee.setPropLat(updateProperty.getPropLat());
        }
        if(updateProperty.getStreet()!=null){
            employee.setStreet(updateProperty.getStreet());
        }
        if(updateProperty.getCity()!=null){
            employee.setCity(updateProperty.getCity());
        }
        if(updateProperty.getCountry()!=null){
            employee.setCountry(updateProperty.getCountry());
        }
        if(updateProperty.getAbout()!=null){
            employee.setAbout(updateProperty.getAbout());
        }
        if(updateProperty.getDisplay()!=null){
            employee.setDisplay(updateProperty.getDisplay());
        }
        if(updateProperty.getImage()!=null){
            employee.setImage(updateProperty.getImage());
        }
        if(updateProperty.getWebsite()!=null){
            employee.setWebsite(updateProperty.getWebsite());
        }
        if(updateProperty.getBookingUrl()!=null){
            employee.setBookingUrl(updateProperty.getBookingUrl());
        }
        Property updatedProperty = propertyService.updateProperty(employee);
        return Response.ok(updatedProperty).build();
    }

    /**
     * Take this away.
     * @param id
     * @param is
     * @return
     */
    @POST
    @Path("picture")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @GlobatiAuthentication
    public Response updatePropertyImage(@FormDataParam("id") Long id,
                                        @FormDataParam("file") InputStream is
                                        ){
        try{
            propertyService.replaceImage(id, is);
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException(Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("changepassword")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(ChangePassword passwords){
        try{
            if(propertyService.changePassword(passwords.getPropertyId(), passwords.getOldPassword(), passwords.getNewPassword())){
                return Response.ok("Password changed").build();
            }
            else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new WebException("Could not change password", Response.Status.FORBIDDEN);
        }
    }

    @POST
    @Path("sendemailtochangepassword")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response sendUsernameAndPassword(String email){
        log.debug("api sendUserNameAndPassword()");
        try{
            propertyService.sendEmailToChangePassword(email);
            return Response.ok("changepassword email sent").build();
        }catch(Exception e){
            throw new WebException("Could not send email", Response.Status.CONFLICT);
        }
    }

    @POST
    @Path("changepasswordwithtoken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePasswordWithToken(ChangePasswordWithToken changePasswordWithToken){
        try{
            propertyService.changePasswordWithToken(changePasswordWithToken.getToken(), changePasswordWithToken.getPassword());
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not change the password with token", Response.Status.CONFLICT);
        }
    }

    @POST
    @Path("mail")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response sendMail(Email list){
        try{
            log.debug("sendMail()");
            Property employee = propertyService.getPropertyById(list.getId());
            SendMail.sendGuestMail(employee, list.getEmails());
            return Response.ok("mail sent").build();
        }catch(Exception e){
            throw new WebException("Could not send emails", Response.Status.BAD_REQUEST);
        }
    }
}
