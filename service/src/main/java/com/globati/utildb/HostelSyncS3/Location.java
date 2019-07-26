package com.globati.utildb.HostelSyncS3;

public class Location {
    private String city;
    private String contry;

    public Location(String city, String contry) {
        this.city = city;
        this.contry = contry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }
}
