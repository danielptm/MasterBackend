package com.globati.resources;

import com.globati.resources.exceptions.WebException;
import com.globati.service.EmployeeService;
import com.globati.service.RecommendationService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("public")
public class PublicMobile {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecommendationService recommendationService;

    @PUT
    @Path("increment/website/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementWebsite(@PathParam("id") Long id) {
        try {
            return Response.ok(employeeService.incrementWebsiteCounter(id)).build();
        } catch (Exception e) {
            throw new WebException("Could not increment the website counter.", Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("increment/mobile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementMobile(@PathParam("id") Long id) {
        try{
            return Response.ok(employeeService.incrementMobileCounter(id)).build();

        }catch(Exception e){
            throw new WebException("Could not increment mobile counter", Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("employee/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByUsername(@PathParam("username") String username){
        try{
            return Response.ok(employeeService.getEmployeeByUserName(username)).build();
        } catch(Exception e){
            throw new WebException("Could not get employee by username" + username, Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("recommendation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecommendationsByEmployeeId(@PathParam("id") Long id) {
        try {
            return Response.ok(recommendationService.getRecommendationByEmployeeId(id)).build();
        }catch(Exception e) {
            throw new WebException("Could not get recommendations by employee id.", Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        try {
            return Response.ok(employeeService.getAllActiveEmployees()).build();
        } catch (Exception e) {
            throw new WebException("Could not get employees by city.", Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("employee/recommendations/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeAndItemsByUsername(@PathParam("username") String username) {
        try {
            return Response.ok(employeeService.getEmployeeByIdWithRecommdations(username)).build();
        } catch (Exception e) {
            throw new WebException("Could not get employee by city.", Response.Status.NOT_FOUND);
        }
    }
}
