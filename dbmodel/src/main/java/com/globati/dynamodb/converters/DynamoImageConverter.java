package com.globati.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.util.Mapper;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamoImageConverter implements DynamoDBTypeConverter<String, DynamoImage> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoImageConverter.class);


    public String convert(DynamoImage object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("DynamoImageConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public DynamoImage unconvert(String object) {
        DynamoImage dynamoImage = null;
        try {
            dynamoImage = Mapper.getMapper().readValue(object, DynamoImage.class);
        } catch (Exception e) {
            LOGGER.error("DynamoImageConverter exception: ");
            e.printStackTrace();
        }
        return dynamoImage;
    }
}
