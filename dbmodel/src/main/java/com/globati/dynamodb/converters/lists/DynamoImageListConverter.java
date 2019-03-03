package com.globati.dynamodb.converters.lists;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;

import java.util.List;

public class DynamoImageListConverter  implements DynamoDBTypeConverter<String, List<DynamoImage>> {
    public String convert(List<DynamoImage> object) {
        return null;
    }

    public List<DynamoImage> unconvert(String object) {
        return null;
    }
}
