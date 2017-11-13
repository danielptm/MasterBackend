package com.globati.resources;

import com.globati.adapter.EmployeeAdapter;
import com.globati.dbmodel.Employee;
import com.globati.deserialization_beans.response.employee.ResponseEmployee;
import com.globati.resources.exceptions.WebException;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service_beans.guest.EmployeeAndItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 *
 * Refactoring:
 * Ok for now, the only big problem that I see is this resource is not using authentication.
 * And employee data is sent. This is for the most part completely ok, because the employee knows that.
 *
 *
 *
 */
@Component
@Path("guest")
public class GuestResource {

    private static final Logger log = LogManager.getLogger(AuthenticationResource.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DealService dealService;

    @Autowired
    EmployeeAdapter employeeAdapter;

    /**
     * Called when logging into myglobatiadmin.com.
     *
     * See if this method is necesseary
     *
     * @param id
     * @return
     */

    @GET
    @Path("{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guestLogin(@PathParam("username") String id){
        EmployeeAndItems employeeAndNearbyDeals;
        try{
            employeeAndNearbyDeals = employeeService.getItemsForEmployee(id);
            return Response.ok(employeeAndNearbyDeals).build();
        }catch(Exception e){
            throw new WebException("Could not find splash page for employee",Response.Status.CONFLICT);
        }
    }


    /**
     * Called when going to globati.com/username
     *
     * @param id
     * @return
     */
    @GET
    @Path("visitprofile/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response visitProfile(@PathParam("username") String id) throws ServiceException, UserDoesNotExistException, IllegalUserNameException {
        EmployeeAndItems employeeAndNearbyDeals;
        employeeAndNearbyDeals = employeeService.getItemsForEmployeeAndIncrement(id);
        return Response.ok(employeeAndNearbyDeals).build();

    }

    /**
     * Called in globati.com/connect/userid
     *
     * This method can be taken away because we are not doing deals.
     *
     * @param id
     * @return
     */
    @GET
    @Path("userid/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeDataByUserName(@PathParam("id") Long id){
        try{
            Employee employee =  employeeService.getEmployeeById(id);
            return Response.ok(employee).build();
        }catch(Exception e){
            throw new WebException("Could not find employee  by username", Response.Status.CONFLICT);
        }
    }

    /**
     * Called in globati.com when a user searches for employees by location
     * @param place
     * @return
     */
    @GET
    @Path("place/{place}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSplashData(@PathParam("place") String place) {
        List<Employee> employeesInPlace;
        try{
            employeesInPlace = employeeService.getEmployeesByCity(place);
            if(employeesInPlace.size()==0){
                employeesInPlace = employeeService.getEmployeesByCountry(place);
            }
            if(employeesInPlace.size()>0) {
                return Response.ok(employeesInPlace).build();
            }
            else{
                throw new Exception("Could not locate any employees for this place "+place);
            }
        }catch(Exception e){
            throw new WebException("Could not get employees for place "+place, Response.Status.EXPECTATION_FAILED);
        }
    }


}
