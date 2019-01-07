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
    @JoinColumn(name="tourstopid")
    @JsonBackReference
    TourStop tourstop;

    public TourStopImage() {}

    public TourStopImage(TourStop tourStop, String path){
        this.imagePath = path;
        this.tourstop = tourStop;
    }

    public TourStop getTourstop() {
        return tourstop;
    }

    public void setTourstop(TourStop tourstop) {
        this.tourstop = tourstop;
    }
}
