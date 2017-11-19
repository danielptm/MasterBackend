package com.globati.dbmodel;

import com.globati.enums.Verified;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by daniel on 1/17/17.
 */

@Entity
@Table(name = "employeeInfo")
public class EmployeeInfo extends BaseEntity{

    @Column(name="employeeid")
    Long employeeId;
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

    @Column(name="verified")
    @Enumerated(EnumType.STRING)
    Verified _verified;

    public EmployeeInfo(Long _employeeId) {
        this.employeeId = _employeeId;
        this.dateCreated = new Date();

    }

    public EmployeeInfo(Long employeeId, String facebookid) {
        this.employeeId = employeeId;
        this.facebookId = facebookid;
        this.dateCreated = new Date();
    }

    public EmployeeInfo(){}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Verified get_verified() {
        return _verified;
    }

    public void set_verified(Verified _verified) {
        this._verified = _verified;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "employeeId=" + employeeId +
                ", globatiPassword='" + globatiPassword + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", lastLogin=" + lastLogin +
                ", dateCreated=" + dateCreated +
                ", authToken='" + authToken + '\'' +
                ", tokenExpiration='" + tokenExpiration + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", _verified=" + _verified +
                '}';
    }

}
