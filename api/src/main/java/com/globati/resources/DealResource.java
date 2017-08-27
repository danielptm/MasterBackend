package com.globati.resources;

import com.globati.dbmodel.Deal;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.utildb.HelpObjects.BusinessEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by daniel on 12/27/16.
 *
 * Refactoring:
 * ok
 *
 * I have decided to leave the GuestDeal object the way it is. Even though it is poor design.
 * The reason being that its a lot of work to break it out, it creates extra code, and at the moment there is no reason
 * to make this so loosely coupled from the data layer. We can make this refactor if we need to later.
 *
 */

@Component
@Path("deals")
@GlobatiAuthentication
public class DealResource {

    private static final Logger log = LogManager.getLogger(DealResource.class);

    @Autowired
    DealService dealService;

    @Autowired
    EmployeeService employeeService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNearByDeals(@QueryParam("id") Long id, @QueryParam("country") String country){
        try{
            List<Deal> deals = dealService.getNearbyActiveDeals(country, id);
            return Response.ok(deals).build();
        }catch(Exception e){
            throw new WebException("Could not get nearby deals", Response.Status.CONFLICT);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Deal deal){
        try{
            dealService.updateDeal(deal);
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not update deal", Response.Status.CONFLICT);
        }
    }


    /**
     * This whole process from angular to this point is really bad and needs to be refactored.
     * We need a typescript object angular side for this object. I fixed it a little bit on this side,
     * by sending in the employee id instead of the whole emplyoee object
     * @param businessEmail
     * @return
     */

    @POST
    @Path("mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendRecruitmentMail(BusinessEmail businessEmail){
        System.out.println(businessEmail.toString());
        try{
            com.globati.dbmodel.Employee employee = this.employeeService.getEmployeeById(businessEmail.getEmployeeId());
            dealService.sendRecruitmentMail(employee, businessEmail.getBusinessEmail(), businessEmail.getBusinessName());
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not send recruitment mail", Response.Status.CONFLICT);
        }
    }
}
