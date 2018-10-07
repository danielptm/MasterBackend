package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BusinessImage<T extends BusinessEntity> extends ImageEntity {

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    private Tour tour;

    public BusinessImage(){}

    public BusinessImage(T entity, String path) {
        this.tour = tour;
        this.path = path;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessImage{");
        sb.append("tour=").append(tour);
        sb.append('}');
        return sb.toString();
    }
}
