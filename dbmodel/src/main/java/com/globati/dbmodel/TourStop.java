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

    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<BusinessImage> tourImages;

    public TourStop() {}

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

    public List<BusinessImage> getTourImages() {
        return tourImages;
    }

    public void setTourImages(List<BusinessImage> tourImages) {
        this.tourImages = tourImages;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TourStop{");
        sb.append("tour=").append(tour);
        sb.append(", stopOrder=").append(stopOrder);
        sb.append(", tourImages=").append(tourImages);
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
