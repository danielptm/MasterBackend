package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;
import com.globati.dynamodb.converters.lists.DynamoTourListConverter;

import java.util.List;
import java.util.UUID;

public class DynamoTour extends DynamoBusinessInfo{

    @DynamoDBAttribute (attributeName = "tourImages")
    List<DynamoImage> images;
    @DynamoDBAttribute
    List<DynamoTourStop> tourStops;

    public DynamoTour() {
        super();
    }
}
