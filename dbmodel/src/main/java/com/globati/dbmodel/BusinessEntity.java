package com.globati.dbmodel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by daniel on 4/2/17.
 */
@MappedSuperclass
public abstract class BusinessEntity extends BaseEntity {

    @Column(name = "targetlat")
    protected double _targetLat;
    @Column(name = "targetlong")
    protected double _targetLong;
    @Column(name = "distance")
    protected double _distance;
    @Column(length = 100, name = "street")
    protected String _street;
    @Column(length = 100, name = "city")
    protected String _city;
    @Column(length = 100, name = "country")
    protected String _country;
    @Column(name = "image")
    protected String _image;
    @Column(length = 3000, name = "description")
    protected String _description;
    @Column(length = 100, name = "location")
    protected String _location;
    @Column(name = "active")
    protected boolean _active;
    @Column(length = 100, name = "title")
    protected String _title;
    @Column(name="dateinactive")
    protected Date _dateInactive;
    @Column(name="image2")
    protected String _image2;
    @Column(name="image3")
    protected String _image3;

    public double get_targetLat() {
        return _targetLat;
    }

    public void set_targetLat(double _targetLat) {
        this._targetLat = _targetLat;
    }

    public double get_targetLong() {
        return _targetLong;
    }

    public void set_targetLong(double _targetLong) {
        this._targetLong = _targetLong;
    }

    public double get_distance() {
        return _distance;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    public String get_street() {
        return _street;
    }

    public void set_street(String _street) {
        this._street = _street;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public boolean is_active() {
        return _active;
    }

    public void set_active(boolean _active) {
        this._active = _active;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public Date get_dateInactive() {
        return _dateInactive;
    }

    public void set_dateInactive(Date _dateInactive) {
        this._dateInactive = _dateInactive;
    }

    public String get_image2() {
        return _image2;
    }

    public void set_image2(String _image2) {
        this._image2 = _image2;
    }

    public String get_image3() {
        return _image3;
    }

    public void set_image3(String _image3) {
        this._image3 = _image3;
    }


}
