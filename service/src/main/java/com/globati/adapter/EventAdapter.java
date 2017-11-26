package com.globati.adapter;

import com.globati.dbmodel.Event;
import com.globati.deserialization_beans.response.employee.ResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EventAdapter {

    @Autowired
    ImageAdapater imageAdapater;

    public static final Logger log = LogManager.getLogger(EventAdapter.class);


    public ResponseEvent getAndTranslateAnEvent(Event event) throws Exception {

            ResponseEvent responseEvent = new ResponseEvent(
                    event.getId(), event.getCity(), event.getCountry(),
                    event.getRepeat(), event.getDescription(), event.getLocation(),
                    event.getStreet(), event.getTargetLat(), event.getTargetLong(),
                    event.getTitle(), imageAdapater.translateEventImages(event.getEventimages()),
                    event.getDate());

        return responseEvent;
    }
}
