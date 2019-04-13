package com.globati.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.converters.lists.DynamoImageListConverter;
import com.globati.enums.Category;

import java.util.List;

public class DynamoRecommendation extends DynamoBusinessInfo {

    @DynamoDBAttribute (attributeName = "recommendationImages")
    @DynamoDBTypeConverted(converter = DynamoImageListConverter.class)
    List<DynamoImage> images;
    @DynamoDBAttribute (attributeName = "category")
    Category category;
    @DynamoDBAttribute (attributeName = "title")
    String title;

    public DynamoRecommendation() {
        super();
    }

    public List<DynamoImage> getImages() {
        return images;
    }

    public void setImages(List<DynamoImage> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
