package com.globati.resources;

import com.globati.resources.annotations.GlobatiAuthentication;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;

import javax.persistence.NamedStoredProcedureQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("tour")
@GlobatiAuthentication
public class Tour {

    private static Logger log = LogManager.getLogger(Tour.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTourById(@QueryParam("id") Long id) {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTour(){
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTour(){
        return null;
    }


}
