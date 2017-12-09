package com.globati.deserialization_beans.response.employee;

public class ResponseFlight {

    String time;
    String dateBooked;
    String route;
    String comission;

    public ResponseFlight(String time, String dateBooked, String route, String comission) {
        this.time = time;
        this.dateBooked = dateBooked;
        this.route = route;
        this.comission = comission;
    }

    public String getTime() {
        return time;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public String getRoute() {
        return route;
    }

    public String getComission() {
        return comission;
    }

}
