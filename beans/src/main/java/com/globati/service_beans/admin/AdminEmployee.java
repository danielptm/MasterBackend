package com.globati.user_beans.admin;

import com.globati.super_beans.SuperEmployee;

/**
 * Created by daniel on 8/7/17.
 */
public class AdminEmployee extends SuperEmployee {

    String paypalEmail;
    double addAmount;
    double add2Month;
    double add3Month;
    Integer visitCounter;

    public AdminEmployee(Long id,
                         String firstName,
                         String image1,
                         String email,
                         String about,
                         String welcomeMail,
                         String recruitmentMail,
                         String instagramUser,
                         String instagramUserId,
                         String instagramUserToken,
                         Double propLat,
                         Double propLong,
                         String street,
                         String city,
                         String country,
                         String display,
                         String globatiUsername,
                         boolean facebookProfile,
                         String paypalEmail,
                         double addAmount,
                         double add2Month,
                         double add3Month,
                         Integer visitCounter

    ) {
        super(
                id, firstName, image1, email, about, welcomeMail, recruitmentMail, instagramUser,
                instagramUserId, instagramUserToken, propLat, propLong, street, city, country,
                display, globatiUsername, facebookProfile
        );
        this.paypalEmail = paypalEmail;
        this.addAmount = addAmount;
        this.add2Month = add2Month;
        this.add3Month = add3Month;
        this.visitCounter = visitCounter;

    }


    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public double getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(double addAmount) {
        this.addAmount = addAmount;
    }

    public double getAdd2Month() {
        return add2Month;
    }

    public void setAdd2Month(double add2Month) {
        this.add2Month = add2Month;
    }

    public double getAdd3Month() {
        return add3Month;
    }

    public void setAdd3Month(double add3Month) {
        this.add3Month = add3Month;
    }

    public Integer getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(Integer visitCounter) {
        this.visitCounter = visitCounter;
    }


}
