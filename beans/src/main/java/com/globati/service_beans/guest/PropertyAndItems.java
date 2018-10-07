package com.globati.service_beans.guest;

import com.globati.dbmodel.Property;

public class PropertyAndItems {

    Property employee;

    public PropertyAndItems(Property employee) {
        this.employee = employee;
    }

    String apiKey;
    boolean facebookProfileCreated = false;

    public Property getProperty() {
        return employee;
    }

    public void setEmployee(Property employee) {
        this.employee = employee;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isFacebookProfileCreated() {
        return facebookProfileCreated;
    }

    public void setFacebookProfileCreated(boolean facebookProfileCreated) {
        this.facebookProfileCreated = facebookProfileCreated;
    }
}
