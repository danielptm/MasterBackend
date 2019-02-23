package com.globati.dynamodb.tour;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;

import java.util.List;

public class DynamoTourStop extends DynamoBusinessInfo{

    @DynamoDBAttribute
    List<DynamoImage> images;
    @DynamoDBAttribute
    int stopOrder;

}
