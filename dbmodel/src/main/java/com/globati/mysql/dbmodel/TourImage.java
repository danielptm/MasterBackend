package com.globati.mysql.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tourimage")
public class TourImage extends ImageEntity{

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    Tour tour;

    public TourImage() {
    }

    public TourImage(Tour tour, String path) {
        this.imagePath = path;
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
