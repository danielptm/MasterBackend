package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;

import java.util.ArrayList;
import java.util.List;

public class DynamoTourStop extends DynamoBusinessInfo {

    @DynamoDBAttribute (attributeName = "title")
    String title;
    @DynamoDBAttribute (attributeName = "tourStopImages")
    List<DynamoImage> images;
    @DynamoDBAttribute
    int stopOrder;
    @DynamoDBAttribute (attributeName = "tourId")
    String tourId;

    public DynamoTourStop() {
        super();
        images = new ArrayList<DynamoImage>();
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

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }
}
