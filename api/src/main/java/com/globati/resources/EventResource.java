package com.globati.resources;

import com.globati.adapter.EventAdapter;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.deserialization_beans.response.employee.ResponseEvent;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.EmployeeService;
import com.globati.service.EventService;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.GlobatiUtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

/**
 * Created by daniel on 12/13/16.
 * <p>
 * Refactoring:
 * ok
 */

@Component
@Path("events")
@GlobatiAuthentication
public class EventResource {

    private static final Logger log = LogManager.getLogger(EventResource.class);

    @Autowired
    EventService eventService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EventAdapter eventAdapter;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response update(@PathParam("id") Long id) {
        try {
            Event event = eventService.getEventById(id);
            event.setActive(false);
            this.eventService.updateEvent(event);
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebException("Could not update event", Response.Status.CONFLICT);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents(@QueryParam("id") Long id) {
        try {
            List<Event> events = this.eventService.getEventsByEmployeeId(id);
            return Response.ok(events).build();
        } catch (Exception e) {
            throw new WebException("Could not get events by id", Response.Status.BAD_REQUEST);
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(com.globati.deserialization_beans.request.Event event) throws Exception {
        try {
            Employee employee = employeeService.getEmployeeById(event.getEmployeeId());
            Event event1 = this.eventService.createEvent(
                    employee,
                    com.globati.utildb.DateTools.getDate(event.getDate()), event.getTargetLat(),
                    event.getTargetLong(), event.getStreet(), event.getCity(), event.getCountry(),
                    event.getTitle(), event.getDescription(), event.getImages());


            ResponseEvent responseEvent = eventAdapter.getAndTranslateAnEvent(event1);

            return Response.ok(responseEvent).build();


        } catch (Exception e) {
            throw e;
        }
    }
}
