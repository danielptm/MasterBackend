package com.globati.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("health-check")
public class HealthCheck {

    @GET
    public Response healthCheck() {
        return Response.ok("Globati up and running...").build();
    }

}
