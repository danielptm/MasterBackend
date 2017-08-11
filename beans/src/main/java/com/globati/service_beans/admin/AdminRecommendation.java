package com.globati.user_beans.admin;

import com.globati.super_beans.SuperRecommendation;

import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public class AdminRecommendation extends SuperRecommendation {

    boolean active;
    Date dateInactive;

    public AdminRecommendation(
            Long employeeId, Long id, String title,
            String description, String location,
            String image1, String image2, String
                    image3, String street, String city,
            String country, Double targetLat, Double targetLong,
            boolean active, Date dateInactive
    ) {
        super(
                employeeId, id, title, description, location,
                image1, image2, image3, street, city,
                country, targetLat, targetLong);

        this.active = active;
        this.dateInactive = dateInactive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateInactive() {
        return dateInactive;
    }

    public void setDateInactive(Date dateInactive) {
        this.dateInactive = dateInactive;
    }
}
