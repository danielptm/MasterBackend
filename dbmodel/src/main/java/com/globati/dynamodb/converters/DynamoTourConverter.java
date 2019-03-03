package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTour;
import com.google.gson.Gson;

public class DynamoTourConverter implements DynamoDBTypeConverter<String, DynamoTour> {

    public String convert(DynamoTour object) {
        return new Gson().toJson(object);
    }

    public DynamoTour unconvert(String object) {
        return new Gson().fromJson(object, DynamoTour.class);
    }
}
