package com.globati.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("public")
public class PublicMobile {

    @PUT
    @Path("increment/website")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementWebsite() {

        return null;
    }

    @PUT
    @Path("increment/mobile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementMobile() {

        return null;
    }

    @GET
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(){
        return null;
    }


}
