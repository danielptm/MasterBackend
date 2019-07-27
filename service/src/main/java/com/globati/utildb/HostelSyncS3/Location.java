package com.globati.utildb.HostelSyncS3;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getCity(), location.getCity()) &&
                Objects.equals(getContry(), location.getContry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getContry());
    }
}
