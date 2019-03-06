package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoRecommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamoRecommendationService {

    public DynamoRecommendation createRecommendation(com.globati.request.Recommendation recommendation) {
        return null;
    }

    public String deleteRecommendation(String id) {
        return null;
    }

    public DynamoRecommendation udpateRecommendation(com.globati.request.Recommendation recommendation){
        return null;
    }

    public DynamoRecommendation getRecommendationById(String id) {
        return null;
    }

    public List<DynamoRecommendation> getRecommendationsByEmployeeName(String name) {
        return null;
    }
}
