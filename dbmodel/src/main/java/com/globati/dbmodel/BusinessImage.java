package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.ImageType;

import javax.persistence.*;

// TODO: Implement builder pattern on this object.
@Entity
@Table(name = "businessimage")
public class BusinessImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    private Tour tour;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private int stopOrder;

    public BusinessImage(){}

    public BusinessImage(Tour tour, String path) {
        this.tour = tour;
        this.path = path;
        this.imageType = ImageType.TOUR;
        this.stopOrder = -1;
    }

    public BusinessImage(Tour tour, String path, int stopOrder) {
        this.tour = tour;
        this.path = path;
        this.imageType = ImageType.TOUR_STOP;
        this.stopOrder = stopOrder;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }

    public int getStopOrder() {
        return stopOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessImage{");
        sb.append("tour=").append(tour);
        sb.append('}');
        return sb.toString();
    }
}
