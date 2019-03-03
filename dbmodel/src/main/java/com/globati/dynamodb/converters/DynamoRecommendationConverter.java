package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.google.gson.Gson;

public class DynamoRecommendationConverter implements DynamoDBTypeConverter<String, DynamoRecommendation> {

    public String convert(DynamoRecommendation object) {
        return new Gson().toJson(object);
    }

    public DynamoRecommendation unconvert(String object) {
        return new Gson().fromJson(object, DynamoRecommendation.class);
    }
}
