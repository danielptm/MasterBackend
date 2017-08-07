package com.globati.user_beans.admin;

import com.globati.super_beans.SuperDeal;

import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public class AdminDeal extends SuperDeal {

    boolean active;
    Date dateInactive;
    String dealType;
    String plan;
    Double cost;
    Date datemade;

    public AdminDeal(
            Long id, double targetLat, double
            targetLong, double distance, String street,
            String city, String country, String image, String description,
            String location, String title, String image2, String image3,
            String website, String email, double nonRecruiterDistance, boolean active,
            Date dateInactive, String dealType, String plan, Double cost, Date datemade
            ) {
        super(
                id, targetLat, targetLong, distance, street, city,
                country, image, description, location, title, image2,
                image3, website, email, nonRecruiterDistance);

        this.active = active;
        this.dateInactive = dateInactive;
        this.dealType = dealType;
        this.plan = plan;
        this.cost = cost;
        this.datemade = datemade;
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

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getDatemade() {
        return datemade;
    }

    public void setDatemade(Date datemade) {
        this.datemade = datemade;
    }



}
