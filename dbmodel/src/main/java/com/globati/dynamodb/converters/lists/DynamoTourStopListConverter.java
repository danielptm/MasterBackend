package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTourStop;

import java.util.List;

public class DynamoTourStopListConverter implements DynamoDBTypeConverter<String, List<DynamoTourStop>> {
    public String convert(List<DynamoTourStop> object) {
        return null;
    }

    public List<DynamoTourStop> unconvert(String object) {
        return null;
    }
}
