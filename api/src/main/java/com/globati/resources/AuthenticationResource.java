package com.globati.resources;

import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service_beans.guest.EmployeeAndItems;
import com.globati.third_party_api.AWSCredentials;
import com.globati.deserialization_beans.request.FacebookLogin;
import com.globati.deserialization_beans.request.PasswordAttempt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
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
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authentication(PasswordAttempt pa){
        log.debug("authentication()");
        try {
             return Response.ok(employeeService.authenticateReceptionist(pa.getUsername(), pa.getPassword())).build();
        }catch(Exception e){
            e.printStackTrace();
            throw new WebException("Password or username did not match", Response.Status.UNAUTHORIZED);
        }
    }

    /**
     * This called when somebody clicks login with facebook. Therefore if somebody is creating a globati
     * profile by loggin in, or just loggin in, this is called.
     *
     * Completely improvised this authentication procedure. So hopefully this is secure. Seems to me like its fine.
     * On the client side, an authentication token is give to the user upon successful authentication. That token is
     * sent here, and used in FacebookTemplate to access the user. The token is used here to retrieve the userId
     * of the person who authenticated on the client side useing the FacebookTemplate. It is matched with the userid
     * sent from the client. If the 2 ids match then the user is authenticated on the globati side. If not, then an
     * exception is thrown.
     *
     *
     *
     * @param facebookLogin
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("facebooklogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response facebooklogin(FacebookLogin facebookLogin) throws ServiceException {
        try {
            Facebook facebook = new FacebookTemplate(facebookLogin.getToken(), "globati");

            String idFromClient= facebookLogin.getUserId();
            String idFromServer = facebook.userOperations().getUserProfile().getId();

            if(idFromClient.equals(idFromServer)){
                EmployeeAndItems profileItems = this.employeeService.createAccountOrLoginWithFacebook(facebookLogin.getUserid(), facebookLogin.getName(), facebookLogin.getEmail(), facebookLogin.getImage());
                return Response.ok(profileItems).build();
            }
            else{
                throw new Exception();
            }
        }
        catch(ServiceException e){
            throw e;
        }
        catch(Exception e){
            throw new WebException("A user send invalid authentication credentials: "+facebookLogin.toString(), Response.Status.BAD_GATEWAY);
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
