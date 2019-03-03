package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;

public class DynamoRecommendationConverter implements DynamoDBTypeConverter<String, DynamoRecommendation> {

    public String convert(DynamoRecommendation object) {
        return null;
    }

    public DynamoRecommendation unconvert(String object) {
        return null;
    }
}
