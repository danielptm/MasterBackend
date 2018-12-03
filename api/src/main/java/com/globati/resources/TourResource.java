package com.globati.resources;

import com.globati.request.tour.Tour;
import com.globati.resources.annotations.GlobatiAuthentication;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("tour")
@GlobatiAuthentication
public class TourResource {

    private static Logger log = LogManager.getLogger(TourResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTourById(@QueryParam("id") Long id) {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTour(Tour tour){
        log.info(tour);
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTour(){
        return null;
    }


}
