package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.ImageType;

import javax.persistence.*;

@Entity
@Table(name = "businessimage")
public class BusinessImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    private Tour tour;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;


    public BusinessImage(){}

    public BusinessImage(Tour tour, String path, ImageType imageType) {
        this.tour = tour;
        this.path = path;
        this.imageType = imageType;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessImage{");
        sb.append("tour=").append(tour);
        sb.append('}');
        return sb.toString();
    }
}
