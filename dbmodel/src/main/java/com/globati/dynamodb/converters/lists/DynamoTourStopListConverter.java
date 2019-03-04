package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.util.Mapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DynamoTourStopListConverter implements DynamoDBTypeConverter<String, List<DynamoTourStop>> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoImageListConverter.class);


    public String convert(List<DynamoTourStop> object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoTourStopListConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public List<DynamoTourStop> unconvert(String object) {
        List<DynamoTourStop> dynamoTourStops = null;
        try {
            dynamoTourStops = Mapper.getMapper().readValue(object, new TypeReference<List<DynamoTourStop>>(){});
        } catch (Exception e) {
            LOGGER.error("DynamotourStopListConverter exception: ");
            e.printStackTrace();
        }
        return dynamoTourStops;
    }
}
