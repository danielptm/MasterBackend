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
    Long _employeeId;
    @Column(name="globatipassword")
    private String _globatiPassword;
    @Column(length=300, name="salt")
    private byte[] _salt;
    @Column(name="lastlogin")
    private Date _lastLogin;
    @Column(name="datecreated")
    private Date _dateCreated;
    @Column(name="authtoken")
    private String _authToken;
    @Column(name="tokenexpiration")
    private String _tokenExpiration;
    @Column(name="facebookid", length=100)
    String _facebookId;

    @Column(name="verified")
    @Enumerated(EnumType.STRING)
    Verified _verified;

    public EmployeeInfo(Long _employeeId) {
        this._employeeId = _employeeId;
        this._dateCreated = new Date();

    }

    public EmployeeInfo(Long _employeeId, String facebookid) {
        this._employeeId = _employeeId;
        this._facebookId = facebookid;
        this._dateCreated = new Date();
    }

    public EmployeeInfo(){}


    public Long get_employeeId() {
        return _employeeId;
    }

    public void set_employeeId(Long _employeeId) {
        this._employeeId = _employeeId;
    }

    public String get_globatiPassword() {
        return _globatiPassword;
    }

    public void set_globatiPassword(String _globatiPassword) {
        this._globatiPassword = _globatiPassword;
    }

    public byte[] get_salt() {
        return _salt;
    }

    public void set_salt(byte[] _salt) {
        this._salt = _salt;
    }

    public Date get_lastLogin() {
        return _lastLogin;
    }

    public void set_lastLogin(Date _lastLogin) {
        this._lastLogin = _lastLogin;
    }

    public Date get_dateCreated() {
        return _dateCreated;
    }

    public void set_dateCreated(Date _dateCreated) {
        this._dateCreated = _dateCreated;
    }

    public String get_authToken() {
        return _authToken;
    }

    public void set_authToken(String _authToken) {
        this._authToken = _authToken;
    }

    public String get_tokenExpiration() {
        return _tokenExpiration;
    }

    public void set_tokenExpiration(String _tokenExpiration) {
        this._tokenExpiration = _tokenExpiration;
    }

    public String get_facebookId() {
        return _facebookId;
    }

    public void set_facebookId(String _facebookId) {
        this._facebookId = _facebookId;
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
                "_employeeId=" + _employeeId +
                ", _globatiPassword='" + _globatiPassword + '\'' +
                ", _salt=" + Arrays.toString(_salt) +
                ", _lastLogin=" + _lastLogin +
                ", _dateCreated=" + _dateCreated +
                ", _authToken='" + _authToken + '\'' +
                ", _tokenExpiration='" + _tokenExpiration + '\'' +
                ", _facebookId='" + _facebookId + '\'' +
                ", _verified=" + _verified +
                '}';
    }

}
