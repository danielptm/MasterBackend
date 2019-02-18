package com.globati.mysql.dbmodel;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by daniel on 1/17/17.
 */

@Entity
@Table(name = "propertyInfo")
public class PropertyInfo extends BaseEntity{

    @Column(name="propertyid")
    Long propertyId;
    @Column(name="globatipassword")
    private String globatiPassword;
    @Column(length=300, name="salt")
    private byte[] salt;
    @Column(name="lastlogin")
    private Date lastLogin;
    @Column(name="datecreated")
    private Date dateCreated;
    @Column(name="authtoken")
    private String authToken;
    @Column(name="tokenexpiration")
    private String tokenExpiration;
    @Column(name="facebookid", length=100)
    String facebookId;

    public PropertyInfo(Long _employeeId) {
        this.propertyId = _employeeId;
        this.dateCreated = new Date();

    }

    public PropertyInfo(Long employeeId, String facebookid) {
        this.propertyId = employeeId;
        this.facebookId = facebookid;
        this.dateCreated = new Date();
    }

    public PropertyInfo(){}

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long employeeId) {
        this.propertyId = employeeId;
    }

    public String getGlobatiPassword() {
        return globatiPassword;
    }

    public void setGlobatiPassword(String globatiPassword) {
        this.globatiPassword = globatiPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(String tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }


    @Override
    public String toString() {
        return "PropertyInfo{" +
                "employeeId=" + propertyId +
                ", globatiPassword='" + globatiPassword + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", lastLogin=" + lastLogin +
                ", dateCreated=" + dateCreated +
                ", authToken='" + authToken + '\'' +
                ", tokenExpiration='" + tokenExpiration + '\'' +
                ", facebookId='" + facebookId + '\'' +
                '}';
    }

}
