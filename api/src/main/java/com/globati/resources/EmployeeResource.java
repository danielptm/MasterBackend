package com.globati.resources;

import com.globati.dbmodel.Employee;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.DealService;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.HelpObjects.ChangePassword;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.EmployeeAndItems;
import com.globati.utildb.SendMail;
import com.globati.deserialization_beans.ChangePasswordWithToken;
import com.globati.deserialization_beans.CreateEmployee;
import com.globati.deserialization_beans.UpdateEmployee;
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
@Path("employees")
public class EmployeeResource{


    //This does work.
    private static final Logger log = LogManager.getLogger(EmployeeResource.class);

    @Context
    UriInfo uri;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    DealService dealService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateEmployee createEmployee) throws UserDoesNotExistException, IllegalUserNameException, UserNameIsNotUniqueException, ServiceException {
            Employee employee = this.employeeService.createEmployee(
                    createEmployee.getFirstName(),
                    createEmployee.getEmail(),
                    createEmployee.getUsername(),
                    createEmployee.getPassword(),
                    createEmployee.getTargetLat(),
                    createEmployee.getTargetLong(),
                    createEmployee.getImage(),
                    createEmployee.getStreet(),
                    createEmployee.getCity(),
                    createEmployee.getCountry()
            );
            return Response.ok(employee).build();

    }

        /**
     * Dude, I am not sure where this is being called in the app, I thought this was at as the name says
     * login(). But a login to myglobatiadmi occurs in AuthenticationResource.
     *
     * This is called after the authenticationResource is accessed, because that just returned credentials. This logs
         * the user in and gets their data.
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
            EmployeeAndItems employeeitems = employeeService.getItemsForEmployee(username);
            return Response.ok(employeeitems).build();
        }catch(Exception e){
            e.printStackTrace();
            throw new WebException("Could not retrieve user by username and password", Response.Status.BAD_REQUEST);
        }
    }



    /**
     *
     *
     * @return
     * @throws ServiceException
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response update(@QueryParam("employeeId") Long employeeId, UpdateEmployee updateEmployee){
        Employee employee = null;
        try{
            employee = employeeService.getEmployeeById(employeeId);

            if(updateEmployee.getEmail()!=null){
                employee.setEmail(updateEmployee.getEmail());
            }
            if(updateEmployee.getUsername()!=null){
                employee.setGlobatiUsername(updateEmployee.getUsername());
            }
            if(updateEmployee.getInstagramUserName()!=null){
                employee.setInstagramUser(updateEmployee.getInstagramUserName());
            }
            if(updateEmployee.getInstagramToken()!=null){
                employee.setInstagramUserToken(updateEmployee.getInstagramToken());
            }
            if(updateEmployee.getInstagramUserId()!=null){
                employee.setInstagramUserId(updateEmployee.getInstagramUserId());
            }
            if(updateEmployee.getPaypal()!=null){
                employee.setPaypalEmail(updateEmployee.getPaypal());
            }
            if(updateEmployee.getDay30()!=null){
                employee.setAddAmount(updateEmployee.getDay30());
            }
            if(updateEmployee.getDay60()!=null){
                employee.setAdd2month(updateEmployee.getDay60());
            }
            if(updateEmployee.getDay90()!=null){
                employee.setAdd3month(updateEmployee.getDay90());
            }
            if(updateEmployee.getPropLong()!=null){
                employee.setPropLong(updateEmployee.getPropLong());
            }
            if(updateEmployee.getPropLat()!=null){
                employee.setPropLat(updateEmployee.getPropLat());
            }
            if(updateEmployee.getStreet()!=null){
                employee.setStreet(updateEmployee.getStreet());
            }
            if(updateEmployee.getCity()!=null){
                employee.setCity(updateEmployee.getCity());
            }
            if(updateEmployee.getCountry()!=null){
                employee.setCountry(updateEmployee.getCountry());
            }
            if(updateEmployee.getAbout()!=null){
                employee.setAbout(updateEmployee.getAbout());
            }
            if(updateEmployee.getWelcomeMail()!=null){
                employee.setWelcomeMail(updateEmployee.getWelcomeMail());
            }
            if(updateEmployee.getRecruitmentMail()!=null){
                employee.setRecruitmentMail(updateEmployee.getRecruitmentMail());
            }
            if(updateEmployee.getDisplay()!=null){
                employee.setDisplay(updateEmployee.getDisplay());
            }
            if(updateEmployee.getImage()!=null){
                employee.setImage(updateEmployee.getImage());
            }
            if(updateEmployee.getImage2()!=null){
                employee.setImage2(updateEmployee.getImage2());
            }
            if(updateEmployee.getImage3()!=null){
                employee.setImage3(updateEmployee.getImage3());
            }
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return Response.ok(updatedEmployee).build();
        }catch(Exception e){
            throw new WebException(Response.Status.CONFLICT);
        }
    }

    @POST
    @Path("picture")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @GlobatiAuthentication
    public Response updateEmployeeImage(@FormDataParam("id") Long id,
                                        @FormDataParam("file") InputStream is
                                        ){
        try{
            employeeService.replaceImage(id, is);
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
            if(employeeService.changePassword(passwords.getEmployeeId(), passwords.getOldPassword(), passwords.getNewPassword())){
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
            employeeService.sendEmailToChangePassword(email);
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
            employeeService.changePasswordWithToken(changePasswordWithToken.getToken(), changePasswordWithToken.getPassword());
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
            Employee employee = employeeService.getEmployeeById(list.getId());
            SendMail.sendGuestMail(employee, list.getEmails());
            return Response.ok("mail sent").build();
        }catch(Exception e){
            throw new WebException("Could not send emails", Response.Status.BAD_REQUEST);
        }
    }
}
