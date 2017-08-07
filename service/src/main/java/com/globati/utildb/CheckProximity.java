package com.globati.utildb;


import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.dbmodel.Recommendation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
public class CheckProximity {

    private static final Logger log = LogManager.getLogger(CheckProximity.class);

    /**
     * This is the haversine formula. I was going to code this myself, but then found this pre-written
     * at the same time when looking up the formula, so I decided to just use it!
     * @param
     * @return
     */

    public static Event getEventProximity(Event event, Employee employee){
        List<Event> lbo = new ArrayList<Event>();
        double lat1 = employee.getPropLat(); double lng1 = employee.getPropLong();
        double lat2 = event.getTargetLat(); double lng2 = event.getTargetLong();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2-lng1);
        double R = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R*c;
        event.setDistance(distance);
        return event;
    }

    public static Recommendation getRecommendationProximity(Recommendation recommendation, Employee employee){
        List<Recommendation> lbo = new ArrayList<Recommendation>();
        double lat1 = employee.getPropLat(); double lng1 = employee.getPropLong();
        double lat2 = recommendation.getTargetLat(); double lng2 = recommendation.getTargetLong();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2-lng1);
        double R = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R*c;
        recommendation.setDistance(distance);
        return recommendation;
    }

    public static Deal getDealProximity(Deal deal, Employee employee){
        List<Deal> lbo = new ArrayList<Deal>();
        double lat1 = employee.getPropLat(); double lng1 = employee.getPropLong();
        double lat2 = deal.getTargetLat(); double lng2 = deal.getTargetLong();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2-lng1);
        double R = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R*c;
        deal.setDistance(distance);
        return deal;

    }

    public static Deal getNonRecruiterProximity(Deal deal, Employee employee){
        List<Deal> lbo = new ArrayList<Deal>();
        double lat1 = employee.getPropLat(); double lng1 = employee.getPropLong();
        double lat2 = deal.getTargetLat(); double lng2 = deal.getTargetLong();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2-lng1);
        double R = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R*c;
        deal.setNonRecruiterDistance(distance);
        return deal;

    }

}
