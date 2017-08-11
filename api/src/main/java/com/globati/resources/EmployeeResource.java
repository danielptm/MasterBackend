package com.globati.resources;


import com.globati.dbmodel.Employee;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.DealService;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.utildb.HelpObjects.ChangePassword;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.server.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.List;
import com.globati.utildb.HelpObjects.Email;
import com.globati.webmodel.*;



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
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(CreateEmployee createEmployee) throws UserDoesNotExistException {
        try {
            this.employeeService.createEmployee(
                    createEmployee.get_firstName(),
                    createEmployee.get_email(),
                    createEmployee.get_username(),
                    createEmployee.get_password(),
                    createEmployee.get_targetLat(),
                    createEmployee.get_targetLong(),
                    createEmployee.get_image(),
                    createEmployee.get_street(),
                    createEmployee.get_city(),
                    createEmployee.get_country()
            );
            return Response.ok().build();
        } catch (UserDoesNotExistException e) {
            throw new WebException("user already exists", Response.Status.EXPECTATION_FAILED);
        } catch (Exception e) {
            throw new WebException("Could not create a new employee at this time", Response.Status.CONFLICT);
        }
    }

        /**
     * Dude, I am not sure where this is being called in the app, I thought this was at as the name says
     * login(). But a login to myglobatiadmi occurs in AuthenticationResource.
     *
     * Next time i figure out what this does, please document it properly.
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
            List<Object> employeeitems = employeeService.getItemsForEmployee(username);
            return Response.ok(employeeitems).build();
        }catch(Exception e){
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
            employee= employeeService.getEmployeeById(employeeId);

            if(updateEmployee.get_email()!=null){
                employee.setEmail(updateEmployee.get_email());
            }
            if(updateEmployee.get_username()!=null){
                employee.setGlobatiUsername(updateEmployee.get_username());
            }
            if(updateEmployee.get_instagramUserName()!=null){
                employee.setInstagramUser(updateEmployee.get_instagramUserName());
            }
            if(updateEmployee.get_instagramToken()!=null){
                employee.setInstagramUserToken(updateEmployee.get_instagramToken());
            }
            if(updateEmployee.get_instagramUserId()!=null){
                employee.setInstagramUserId(updateEmployee.get_instagramUserId());
            }
            if(updateEmployee.get_paypal()!=null){
                employee.setPaypalEmail(updateEmployee.get_paypal());
            }
            if(updateEmployee.get_day30()!=null){
                employee.setAddAmount(updateEmployee.get_day30());
            }
            if(updateEmployee.get_day60()!=null){
                employee.setAdd2month(updateEmployee.get_day60());
            }
            if(updateEmployee.get_day90()!=null){
                employee.setAdd3month(updateEmployee.get_day90());
            }
            if(updateEmployee.get_propLong()!=null){
                employee.setPropLong(updateEmployee.get_propLong());
            }
            if(updateEmployee.get_propLat()!=null){
                employee.setPropLat(updateEmployee.get_propLat());
            }
            if(updateEmployee.get_street()!=null){
                employee.setStreet(updateEmployee.get_street());
            }
            if(updateEmployee.get_city()!=null){
                employee.setCity(updateEmployee.get_city());
            }
            if(updateEmployee.get_country()!=null){
                employee.setCountry(updateEmployee.get_country());
            }
            if(updateEmployee.get_about()!=null){
                employee.setAbout(updateEmployee.get_about());
            }
            if(updateEmployee.get_welcomeMail()!=null){
                employee.setWelcomeMail(updateEmployee.get_welcomeMail());
            }
            if(updateEmployee.get_recruitmentMail()!=null){
                employee.setRecruitmentMail(updateEmployee.get_recruitmentMail());
            }
            if(updateEmployee.get_display()!=null){
                employee.setDisplay(updateEmployee.get_display());
            }
            if(updateEmployee.get_image()!=null){
                employee.setImage(updateEmployee.get_image());
            }
            if(updateEmployee.get_image2()!=null){
                employee.setImage2(updateEmployee.get_image2());
            }
            if(updateEmployee.get_image3()!=null){
                employee.setImage3(updateEmployee.get_image3());
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
        System.out.println(email.toString());
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
            SendMail.sendGuestMail(list);
            return Response.ok("mail sent").build();
        }catch(Exception e){
            throw new WebException("Could not send emails", Response.Status.BAD_REQUEST);
        }
    }
}
