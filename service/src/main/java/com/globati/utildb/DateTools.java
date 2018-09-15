package com.globati.utildb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

}
