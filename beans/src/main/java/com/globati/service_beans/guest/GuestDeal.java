package com.globati.service_beans.guest;

import com.globati.super_beans.SuperDeal;

/**
 * Created by daniel on 8/7/17.
 */
public class GuestDeal extends SuperDeal{

    public GuestDeal(Long id, double targetLat, double targetLong, double distance, String street, String city, String country, String image, String description, String location, String title, String image2, String image3, String website, String email, double nonRecruiterDistance) {
        super(id, targetLat, targetLong, distance, street, city, country, image, description, location, title, image2, image3, website, email, nonRecruiterDistance);
    }
}
