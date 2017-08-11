package com.globati.service_beans.admin;

import com.globati.super_beans.SuperEvent;

import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public class AdminEvent extends SuperEvent{

    Date dateinactive;
    boolean active;
    Date date;
    boolean publicVisible;
    String repeat;

    public AdminEvent(
                    String title, Date date,
                      String description, Double targetLat,
                    Double targetLong, Double distance,
                    String street, String city, String country,
                    String image1, String image2, String image3,
                    String location, Long employeeId, Date dateinactive,
                    boolean active, boolean publicVisible, String repeat

                    ) {
        super(title, date, description, targetLat, targetLong, distance, street, city, country, image1, image2, image3, location, employeeId);
        this.dateinactive = dateinactive;
        this.active = active;
        this.publicVisible = publicVisible;
        this.repeat = repeat;

    }


    public Date getDateinactive() {
        return dateinactive;
    }

    public void setDateinactive(Date dateinactive) {
        this.dateinactive = dateinactive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPublicVisible() {
        return publicVisible;
    }

    public void setPublicVisible(boolean publicVisible) {
        this.publicVisible = publicVisible;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }


}
