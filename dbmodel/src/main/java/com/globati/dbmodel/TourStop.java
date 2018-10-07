package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class TourStop extends BusinessEntity{

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    private Tour tour;
    private Integer stopOrder;
    private List<BusinessImage> images;

    public TourStop() {}

    public TourStop(Tour tour, Integer stopOrder, List<BusinessImage> images) {
        this.tour = tour;
        this.stopOrder = stopOrder;
        this.images = images;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }

    public List<BusinessImage> getImages() {
        return images;
    }

    public void setImages(List<BusinessImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TourStop{");
        sb.append("tour=").append(tour);
        sb.append(", stopOrder=").append(stopOrder);
        sb.append(", images=").append(images);
        sb.append(", targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", active=").append(active);
        sb.append(", title='").append(title).append('\'');
        sb.append(", dateInactive=").append(dateInactive);
        sb.append("} \n");
        return sb.toString();
    }
}
