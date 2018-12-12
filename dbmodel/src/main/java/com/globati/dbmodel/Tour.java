package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tour")
public class Tour extends BusinessEntity{

    @ManyToOne
    @JoinColumn(name="propertyid")
    @JsonBackReference
    //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    private Property property;

    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<BusinessImage> tourImages;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<TourStop> tourStops;

    public Tour(){}

    public Tour(Property property, List<BusinessImage> tourImages, List<TourStop> tourStops) {
        this.property = property;
        this.tourImages = tourImages;
        this.tourStops = tourStops;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<BusinessImage> getTourImages() {
        return tourImages;
    }

    public void setTourImages(List<BusinessImage> tourImages) {
        this.tourImages = tourImages;
    }

    public List<TourStop> getTourStops() {
        return tourStops;
    }

    public void setTourStops(List<TourStop> tourStops) {
        this.tourStops = tourStops;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tour{");
        sb.append("targetLat=").append(targetLat);
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
