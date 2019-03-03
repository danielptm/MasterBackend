package com.globati.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;
import com.globati.dynamodb.converters.lists.DynamoRecommendationListConverter;
import com.globati.mysql.enums.Category;

import java.util.List;

public class DynamoRecommendation extends DynamoBusinessInfo {
    @DynamoDBAttribute (attributeName = "recommendationImages")
    @DynamoDBTypeConverted(converter = DynamoImageListConverter.class)
    List<DynamoImage> images;
    @DynamoDBAttribute
    Category category;
}
