package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tourstop")
public class TourStop extends BusinessEntity{

    @ManyToOne
    @JoinColumn(name="tourid")
    @JsonBackReference
    private Tour tour;
    private Integer stopOrder;

    public TourStop() {}

    public TourStop(Tour tour, Integer stopOrder) {
        this.tour = tour;
        this.stopOrder = stopOrder;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TourStop{");
        sb.append("tour=").append(tour);
        sb.append(", stopOrder=").append(stopOrder);
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
        sb.append('}');
        return sb.toString();
    }
}
