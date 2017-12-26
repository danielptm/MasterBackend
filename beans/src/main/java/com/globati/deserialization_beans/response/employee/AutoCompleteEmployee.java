package com.globati.deserialization_beans.response.employee;

public class AutoCompleteEmployee {
    String globatiUsername;
    String city;
    String image;

    public AutoCompleteEmployee(String globatiUsername, String city, String image) {
        this.globatiUsername = globatiUsername;
        this.city = city;
        this.image = image;
    }

    public String getGlobatiUsername() {
        return globatiUsername;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AutoCompleteEmployee{");
        sb.append("globatiUsername='").append(globatiUsername).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
