package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tourstopimage")
public class TourStopImage extends ImageEntity{
    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    TourStop tourStop;

    public TourStopImage(TourStop tourStop, String path){
        this.path = path;
        this.tourStop = tourStop;
    }
}
