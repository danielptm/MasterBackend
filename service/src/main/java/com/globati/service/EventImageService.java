package com.globati.service;

import com.globati.dbmodel.Event;
import com.globati.dbmodel.EventImage;
import com.globati.repository.EventImageRepository;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EventImageService {

    EventImageRepository eventImageRepository;

    private static final Logger log = LogManager.getLogger(EventImageService.class);


    public void deleteEventImage(Event event) throws ServiceException {
        try{
            eventImageRepository.delete(event.getId());
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** deleteEventImage() eventId: "+event.getId());
            e.printStackTrace();
            throw new ServiceException("there was a problem deleting an EventImage");
        }
    }
}
