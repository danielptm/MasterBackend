package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.util.Mapper;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class DynamoRecommendationConverter implements DynamoDBTypeConverter<String, DynamoRecommendation> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoRecommendationConverter.class);

    public String convert(DynamoRecommendation object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoRecommendationConverter exception: ");
        }
        return toReturn;
    }

    public DynamoRecommendation unconvert(String object) {
        DynamoRecommendation dynamoRecommendation = null;
        try {
            dynamoRecommendation = Mapper.getMapper().readValue(object, DynamoRecommendation.class);
        } catch(Exception e) {
            LOGGER.error("DynamoRecommendationConverter exception: " );
            e.printStackTrace();
        }
        return dynamoRecommendation;
    }
}
