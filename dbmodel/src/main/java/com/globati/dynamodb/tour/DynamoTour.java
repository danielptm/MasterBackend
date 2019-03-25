package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;
import com.globati.dynamodb.converters.lists.DynamoTourListConverter;
import com.sun.webkit.Disposer;

import java.util.List;
import java.util.UUID;

public class DynamoTour extends DynamoBusinessInfo {

    @DynamoDBAttribute (attributeName = "title")
    String title;
    @DynamoDBAttribute (attributeName = "tourImages")
    List<DynamoImage> images;
    @DynamoDBAttribute (attributeName = "tourStops")
    List<DynamoTourStop> tourStops;


    public DynamoTour() {
        super();
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
