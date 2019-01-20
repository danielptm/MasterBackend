package com.globati.resources;

import com.globati.service.TourStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Component
@Path("tour-stop")
public class TourStopResource {

    @Autowired
    TourStopService tourStopService;

    @DELETE
    @Path("/{id}")
//  @GlobatiAuthentication
    public Response deleteTour(@PathParam("id") Long id) {
        return Response.ok(tourStopService.setTourStopToInactive(id)).build();
    }
}
