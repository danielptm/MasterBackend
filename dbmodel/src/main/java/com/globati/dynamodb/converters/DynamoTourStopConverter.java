package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamoTourStopConverter implements DynamoDBTypeConverter<String, DynamoTourStop> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoTourStopConverter.class);

    public String convert(DynamoTourStop object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoTourStopConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public DynamoTourStop unconvert(String object) {
        DynamoTourStop dynamoTourStop = null;
        try {
            dynamoTourStop = Mapper.getMapper().readValue(object, DynamoTourStop.class);
        } catch (Exception e) {
            LOGGER.error("DynamoTourStopConverter exception");
            e.printStackTrace();
        }
        return dynamoTourStop;
    }
}

