package com.globati.dynamodb.converters.lists;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTour;

import java.util.List;

public class DynamoTourListConverter implements DynamoDBTypeConverter<String, List<DynamoTour>> {

    public String convert(List<DynamoTour> object) {
        return null;
    }

    public List<DynamoTour> unconvert(String object) {
        return null;
    }
}
