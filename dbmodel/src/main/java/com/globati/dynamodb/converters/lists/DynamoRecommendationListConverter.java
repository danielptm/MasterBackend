package com.globati.dynamodb.converters.lists;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DynamoRecommendationListConverter implements DynamoDBTypeConverter<String, List<DynamoRecommendation>> {
    public String convert(List<DynamoRecommendation> object) {
        return new Gson().toJson(object);
    }

    public List<DynamoRecommendation> unconvert(String object) {
        Type listType = new TypeToken<ArrayList<DynamoRecommendation>>() {}.getType();
        return new Gson().fromJson(object, listType);
    }
}
