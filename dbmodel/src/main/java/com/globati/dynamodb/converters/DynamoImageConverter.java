package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.google.gson.Gson;

public class DynamoImageConverter implements DynamoDBTypeConverter<String, DynamoImage> {
    public String convert(DynamoImage object) {
        return new Gson().toJson(object);
    }

    public DynamoImage unconvert(String object) {
        return new Gson().fromJson(object, DynamoImage.class);
    }
}
