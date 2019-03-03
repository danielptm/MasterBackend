package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTourStop;

public class DynamoImageConverter implements DynamoDBTypeConverter<String, DynamoImage> {
    public String convert(DynamoImage object) {
        return null;
    }

    public DynamoImage unconvert(String object) {
        return null;
    }
}
