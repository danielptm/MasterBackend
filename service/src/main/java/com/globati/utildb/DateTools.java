package com.globati.utildb;

import com.globati.dbmodel.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/27/16.
 */
public class DateTools {

    private static final Logger log = LogManager.getLogger(DateTools.class);


    /**
     * Returns true if the string matches a date in the format dddd-dd-dd. False otherwise.
     * @param date
     * @return
     */
    public static boolean isValidDateFormat(String date){
        return date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d");
    }

    /**
     * Formats a string in the format of dddd-dd-dd and creates a date object from it.
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public static Date getDate(String stringDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(parsedDate);
    }


    public static Date todaysDate(){
        return java.sql.Date.valueOf(LocalDate.now());
    }

    /**
     * Events repeat for 3 months
     * @param event
     * @return
     */
    public static List<Event> createRepeatingEvents(Event event){
        List<Event> events = new ArrayList<>();
        if( event.getRepeat().equals(Repeat.DAILY) ){
            for(int i=0; i<90; i++){
                Event newEvent = addDaysToEvent(event, i);
                events.add(newEvent);
            }
        }

        else if(event.getRepeat().equals(Repeat.WEEKLY)){
            for(int i=0; i<13; i++){
                Event newEvent = addDaysToEvent(event, i*7);
                events.add(newEvent);
            }
        }
        else{
            events.add(event);
            return events;
        }
        return events;
    }

    public static Event addDaysToEvent(Event event, int i){
        Calendar c = Calendar.getInstance();
        c.setTime(event.getDate());
        c.add(Calendar.DATE, i);
        Event newevent = new Event(event.getEmployee(), c.getTime(), event.getTargetLat(), event.getTargetLong(),event.getStreet(), event.getCity(), event.getCountry(), event.getTitle(), event.getDescription());
        newevent.setDistance(event.getDistance());
        return newevent;
    }

    public static boolean dateIsBeforeCurrentDate(Event eventData){
        java.util.Date date = new java.util.Date();
        return eventData.getDate().before(date);
    }

    public static boolean isWithinTheLast30Days(Date date){

        Date dateToCompareWith = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime( dateToCompareWith );
        c.add(Calendar.DATE, -30);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);

        return c.before(c2);

        }
}
