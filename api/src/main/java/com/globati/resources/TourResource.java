package com.globati.resources;

import com.globati.api.tour.TourRequest;
import com.globati.dynamodb.DynamoProperty;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.service.dynamodb.DynamoTourService;
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
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response getTourById(@PathParam("email") String email) {
        return Response.ok(tourService.getToursByPropertyEmail(email)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response createTour(TourRequest tourRequest) {
        return Response.ok(tourService.createTour(tourRequest)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GlobatiAuthentication
    public Response updateTour(TourRequest tourRequest){
        return Response.ok(tourService.updateTour(tourRequest)).build();
    }

    @DELETE
    @Path("{email}/{id}")
    @GlobatiAuthentication
    public Response deleteTour(@PathParam("email") String email, @PathParam("id") String tourId) {
        DynamoProperty dynamoProperty = tourService.deleteTour(email, tourId);
        return Response.ok().build();
    }

}
