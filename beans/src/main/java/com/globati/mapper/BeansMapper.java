package com.globati.mapper;

import com.globati.user_beans.guest.GuestDeal;
import com.globati.user_beans.Event;
import com.globati.user_beans.guest.GuestEmployee;
import com.globati.user_beans.Recommendation;

/**
 * Created by daniel on 8/7/17.
 *
 * This is a class that contains static methods for mapping data points
 * to User beans. User beans are the objects which are used on the front
 * end. By using these methods, you can produce front end suitable objects
 *
 *
 */
public class BeansMapper {

    public static GuestDeal mapDeal(){
        return null;
    }

    public static Event mapEvent(){
        return null;
    }

    public static Recommendation mapRecommendation(){
        return null;
    }

    public static GuestEmployee mapEmployee(com.globati.dbmodel.Employee employee){
        return new GuestEmployee(
                employee.getId(),
                employee.getFirstName(),
                employee.getImage(),
                employee.getEmail(),
                employee.getAbout(),
                employee.getWelcomeMail(),
                employee.getRecruitmentMail(),
                employee.getInstagramUser(),
                employee.getInstagramUserId(),
                employee.getInstagramUserToken(),
                employee.getPropLat(),
                employee.getPropLong(),
                employee.getStreet(),
                employee.getCity(),
                employee.getCountry(),
                employee.getDisplay(),
                employee.getGlobatiUsername(),
                employee.isFacebookProfile()
                );
    }



}
