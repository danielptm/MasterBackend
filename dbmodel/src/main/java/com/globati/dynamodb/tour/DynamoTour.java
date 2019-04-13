package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;

import java.util.ArrayList;
import java.util.List;

public class DynamoTour extends DynamoBusinessInfo {

    @DynamoDBAttribute (attributeName = "title")
    String title;
    @DynamoDBAttribute (attributeName = "tourImages")
    List<DynamoImage> images;
    @DynamoDBAttribute (attributeName = "tourStops")
    List<DynamoTourStop> tourStops;


    public DynamoTour() {
        super();
        images = new ArrayList<DynamoImage>();
        tourStops = new ArrayList<DynamoTourStop>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DynamoImage> getImages() {
        return images;
    }

    public void setImages(List<DynamoImage> images) {
        this.images = images;
    }

    public List<DynamoTourStop> getTourStops() {
        return tourStops;
    }

    public void setTourStops(List<DynamoTourStop> tourStops) {
        this.tourStops = tourStops;
    }
}
