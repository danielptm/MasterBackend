package com.globati.resources;

import com.globati.request.tour.TourRequest;
import com.globati.service.dynamodb.DynamoTourService;
import com.globati.service.mysql.TourService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("tour")
public class TourResource {

    private static final Logger LOGGER = LogManager.getLogger(TourResource.class);

    @Autowired
    DynamoTourService tourService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTourById(@PathParam("id") String id) {
        return Response.ok(tourService.getToursByPropertyId(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @GlobatiAuthentication
    public Response createTour(TourRequest tourRequest) {
        return Response.ok(tourService.createTour(tourRequest)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @GlobatiAuthentication
    public Response updateTour(TourRequest tourRequest){
        return Response.ok(tourService.updateTour(tourRequest)).build();
    }

    @DELETE
    @Path("/{id}")
//  @GlobatiAuthentication
    public Response deleteTour(@PathParam("id") String id) {
        return Response.ok(tourService.setTourToInactive(id)).build();
    }

}
