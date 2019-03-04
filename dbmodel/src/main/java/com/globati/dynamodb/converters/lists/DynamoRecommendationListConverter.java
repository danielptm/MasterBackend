package com.globati.dynamodb.converters.lists;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class DynamoRecommendationListConverter implements DynamoDBTypeConverter<String, List<DynamoRecommendation>> {

    private static final Logger LOGGER = LogManager.getLogger(DynamoRecommendationListConverter.class);

    public String convert(List<DynamoRecommendation> object) {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("DynamoRecommendationListConverter exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }

    public List<DynamoRecommendation> unconvert(String object) {
        List<DynamoRecommendation> recommendations = null;
        try {
            recommendations = Mapper.getMapper().readValue(object, new TypeReference<List<DynamoRecommendation>>(){});
        } catch (IOException e) {
            LOGGER.error("DynamoRecommendationListConverter exception: ");
            e.printStackTrace();
        }
        return recommendations;
    }
}
