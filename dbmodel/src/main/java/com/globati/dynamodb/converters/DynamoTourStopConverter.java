package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTourStop;

public class DynamoTourStopConverter implements DynamoDBTypeConverter<String, DynamoTourStop> {

    public String convert(DynamoTourStop object) {
        return null;
    }

    public DynamoTourStop unconvert(String object) {
        return null;
    }
}
