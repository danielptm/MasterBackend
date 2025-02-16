package com.globati.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("health-check")
public class HealthCheck {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthCheck() {
        return Response.ok("Globati up and running...").build();
    }

}
