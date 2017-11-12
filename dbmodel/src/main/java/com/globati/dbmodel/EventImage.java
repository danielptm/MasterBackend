package com.globati.dbmodel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eventimage")
public class EventImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name="eventid")
    private Event event;

    public EventImage(){}

    public EventImage(Event event, String path){
        this.event = event;
        this.path = path;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
