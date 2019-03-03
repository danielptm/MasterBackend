package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DynamoTourStopListConverter implements DynamoDBTypeConverter<String, List<DynamoTourStop>> {
    public String convert(List<DynamoTourStop> object) {
        return new Gson().toJson(object);
    }

    public List<DynamoTourStop> unconvert(String object) {
        Type listType = new TypeToken<ArrayList<DynamoTourStop>>() {}.getType();
        return new Gson().fromJson(object, listType);
    }
}
