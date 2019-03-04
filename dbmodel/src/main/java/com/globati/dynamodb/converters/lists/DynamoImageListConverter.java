package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.DynamoTourStopConverter;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.util.Mapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DynamoImageListConverter  implements DynamoDBTypeConverter<String, List<DynamoImage>> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoImageListConverter.class);

    public String convert(List<DynamoImage> object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoImageListConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public List<DynamoImage> unconvert(String object) {
        List<DynamoImage> dynamoImages = null;
        try {
            dynamoImages = Mapper.getMapper().readValue(object, new TypeReference<List<DynamoImage>>(){});
        } catch (Exception e) {
            LOGGER.error("DynamoImageListConverter exception: ");
            e.printStackTrace();
        }
        return dynamoImages;
    }
}
