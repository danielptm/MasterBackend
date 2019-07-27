package com.globati.utildb.HostelSyncS3;

import java.util.Objects;

public class Location {
    private String city;
    private String country;

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getCity(), location.getCity()) &&
                Objects.equals(getCountry(), location.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getCountry());
    }
}
