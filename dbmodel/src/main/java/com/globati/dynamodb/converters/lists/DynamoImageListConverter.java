package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DynamoImageListConverter  implements DynamoDBTypeConverter<String, List<DynamoImage>> {
    public String convert(List<DynamoImage> object) {
        return new Gson().toJson(object);
    }

    public List<DynamoImage> unconvert(String object) {
        Type listType = new TypeToken<ArrayList<DynamoImage>>() {}.getType();
        return new Gson().fromJson(object, listType);
    }
}
