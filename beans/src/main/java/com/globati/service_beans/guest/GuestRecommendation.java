package com.globati.user_beans.guest;

import com.globati.super_beans.SuperRecommendation;

/**
 * Created by daniel on 8/7/17.
 */
public class GuestRecommendation extends SuperRecommendation{
    public GuestRecommendation(Long employeeId, Long id, String title, String description, String location, String image1, String image2, String image3, String street, String city, String country, Double targetLat, Double targetLong) {
        super(employeeId, id, title, description, location, image1, image2, image3, street, city, country, targetLat, targetLong);
    }
}
