package com.globati.user_beans.guest;

import com.globati.super_beans.SuperEmployee;

/**
 * Created by daniel on 8/7/17.
 */
public class GuestEmployee extends SuperEmployee {

    public GuestEmployee(){}

    public GuestEmployee(Long id, String firstName, String image1, String email, String about, String welcomeMail, String recruitmentMail, String instagramUser, String instagramUserId, String instagramUserToken, Double propLat, Double propLong, String street, String city, String country, String display, String globatiUsername, boolean facebookProfile) {
        super(id, firstName, image1, email, about, welcomeMail, recruitmentMail, instagramUser, instagramUserId, instagramUserToken, propLat, propLong, street, city, country, display, globatiUsername, facebookProfile);
    }
}
