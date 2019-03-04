package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamoTourConverter implements DynamoDBTypeConverter<String, DynamoTour> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoTourConverter.class);

    public String convert(DynamoTour object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e ){
            LOGGER.error("DynamoTourConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public DynamoTour unconvert(String object) {
        DynamoTour dynamoTour = null;
        try {
            dynamoTour = Mapper.getMapper().readValue(object, DynamoTour.class);
        }catch (java.io.IOException e) {
            LOGGER.error("DynamoTourConverter exception: ");
            e.printStackTrace();
        }
        return dynamoTour;
    }
}
