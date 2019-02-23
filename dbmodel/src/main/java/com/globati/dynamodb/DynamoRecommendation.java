package com.globati.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.mysql.enums.Category;

import java.util.List;

public class DynamoRecommendation extends DynamoBusinessInfo {
    @DynamoDBAttribute
    List<DynamoImage> images;
    @DynamoDBAttribute
    Category category;
}
