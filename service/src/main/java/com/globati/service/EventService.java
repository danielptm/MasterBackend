package com.globati.service;


import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.repository.EventRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.CheckProximity;
import com.globati.utildb.DateTools;
import com.globati.utildb.GlobatiUtilException;
import com.globati.utildb.ImageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
@Service
public class EventService {

    private static final Logger log = LogManager.getLogger(DealService.class);

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EmployeeService employeeService;

    EventService(){}

    EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Employee employee, Date date, double targetLat, double targetLong, String street, String city, String country, String title, String description, String imageName1, String imageName2, String imageName3 ) throws ServiceException, GlobatiUtilException {
        log.info("createEvent(): employeeId: "+employee.getId());
        Event event=null;
        try {
            event = new Event(employee, date, targetLat, targetLong, street, city, country, title, description, imageName1, imageName2, imageName3 );
//            Event event2 = CheckProximity.getEventProximity(event, employee);
            //I believe this is not necessary anymore because the distance is being on client side now
            return eventRepository.save(event);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createEvent(): employeeId: "+employee.getId());
            e.printStackTrace();
            throw new ServiceException("Could not create event "+event.toString(), e);
        }

    }

    public Event getEventById(Long id) throws ServiceException {
        log.info("getEventById(): id: "+id);
        try {
            return eventRepository.findOne(id);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEventById(): "+id);
            e.printStackTrace();
            throw new ServiceException("Could not get event by id: "+id, e);
        }
    }

    public List<Event> getEventsByEmployeeId(Long id) throws ServiceException {
        log.info("getEventsByEmployeeId(): id: "+id);
        try{
            List<Event> events = eventRepository.getAllEventsBy_employee_id(id, true);
            for(Event event: events){
                CheckProximity.getEventProximity(event, event.getEmployee());
            }
            return events;
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEventsByEmployeeId(): employeeId: "+id);
            e.printStackTrace();
            throw new ServiceException("Could not get events by Employee id: "+id,e);
        }
    }

    public Event updateEvent(Event event) throws ServiceException {
        try{
            return eventRepository.save(event);
        }catch(Exception e){
            log.error("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateEvent(): eventId: "+event.getId());
            e.printStackTrace();
            throw new ServiceException("Could not update event "+event.toString(), e);
        }
    }

    public void deleteEvent(Event event) throws ServiceException{
        try{
            eventRepository.delete(event);
        }catch(Exception e){
            log.error("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: deleteEvent(): eventId:  "+event.getId() );
            e.printStackTrace();
            throw new ServiceException("Could not delete event "+event.toString(), e);
        }
    }

    public List<Event> getNearByEvents(String country, Long id) throws ServiceException {
        List<Event> nearbyevents= new ArrayList<>();
        try {
            Employee employee = employeeService.getEmployeeById(id);
            List<Event> events = eventRepository.findByCountry(country);
            for(Event event: events){
                Event checkedEvent = CheckProximity.getEventProximity(event, employee);
                if(checkedEvent.getDistance()<25 && checkedEvent.getDistance()!=0.0){
                    nearbyevents.add(checkedEvent);
                }
            }
            return nearbyevents;
        }catch(Exception e){
            log.error("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getNearByEvents(): employeeId: "+id);
            e.printStackTrace();
            throw new ServiceException("Could not get near by events for country: "+country, e);
        }
    }

    public List<Event> getAllActiveEvents(){
        return this.eventRepository.getAllActiveEvents(true);
    }


    @Scheduled(cron = "0 35 4 * * ?")
    public void getAllEventsAndSetExpiredEventsToNotActive() throws ServiceException {
        List<Event> events = getAllActiveEvents();

        for(Event e:events){
            if( DateTools.dateIsBeforeCurrentDate(e) && e.isActive() ){
                e.setActive(false);
                updateEvent(e);
            }
        }
    }
}
