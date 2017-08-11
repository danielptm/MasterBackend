package com.globati.service_beans.guest;

import com.globati.super_beans.SuperEvent;

import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public class GuestEvent extends SuperEvent{
    public GuestEvent(String title, Date date, String description, Double targetLat, Double targetLong, Double distance, String street, String city, String country, String image1, String image2, String image3, String location, Long employeeId) {
        super(title, date, description, targetLat, targetLong, distance, street, city, country, image1, image2, image3, location, employeeId);
    }
}
