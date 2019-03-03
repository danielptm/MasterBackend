package com.globati.dynamodb.converters.lists;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;

import java.util.List;

public class DynamoRecommendationListConverter implements DynamoDBTypeConverter<String, List<DynamoRecommendation>> {
    public String convert(List<DynamoRecommendation> object) {
        return null;
    }

    public List<DynamoRecommendation> unconvert(String object) {
        return null;
    }
}
