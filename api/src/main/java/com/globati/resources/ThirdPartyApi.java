package com.globati.resources;


import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Component
@Path("thirdparty")
public class ThirdPartyApi {

    @GET
    @Path("places/{path}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces(@PathParam("path") String path){



        return Response.ok().build();
    }
}
