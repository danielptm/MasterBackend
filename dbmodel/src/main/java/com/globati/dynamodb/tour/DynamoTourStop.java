package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;

import java.util.List;

public class DynamoTourStop extends DynamoBusinessInfo{

    @DynamoDBAttribute (attributeName = "tourStopImages")
    @DynamoDBTypeConverted(converter = DynamoImageListConverter.class)
    List<DynamoImage> images;
    @DynamoDBAttribute
    int stopOrder;

}
