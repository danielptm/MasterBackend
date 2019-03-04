package com.globati.dynamodb.converters.lists;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DynamoTourListConverter implements DynamoDBTypeConverter<String, List<DynamoTour>> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoTourListConverter.class);


    public String convert(List<DynamoTour> object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoTourListConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public List<DynamoTour> unconvert(String object) {
        List<DynamoTour> dynamoTours = null;
        try {
            dynamoTours = Mapper.getMapper().readValue(object, new TypeReference<List<DynamoTour>>(){});
        } catch (Exception e) {
            LOGGER.error("DynamoTourListConverter exception: ");
            e.printStackTrace();
        }
        return dynamoTours;
    }
}
