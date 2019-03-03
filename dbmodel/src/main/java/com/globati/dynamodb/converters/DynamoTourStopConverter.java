package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DynamoTourStopConverter implements DynamoDBTypeConverter<String, DynamoTourStop> {

    public String convert(DynamoTourStop object) {
        return new Gson().toJson(object);
    }

    public DynamoTourStop unconvert(String object) {
        return new Gson().fromJson(object, DynamoTourStop.class);
    }
}
