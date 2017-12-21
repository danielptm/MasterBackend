package com.globati.deserialization_beans.response.employee;

public class ResponseHotel {

    String time;
    String dateBooked;
    String Hotel;
    String comission;

    public ResponseHotel(String time, String dateBooked, String hotel, String comission) {
        this.time = time;
        this.dateBooked = dateBooked;
        Hotel = hotel;
        this.comission = comission;
    }

    public String getTime() {
        return time;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public String getHotel() {
        return Hotel;
    }

    public String getComission() {
        return comission;
    }
}
