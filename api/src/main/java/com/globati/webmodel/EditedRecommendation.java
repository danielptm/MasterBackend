package com.globati.webmodel;

import java.util.Date;

/**
 * Created by daniel on 12/30/16.
 */
public class EditedRecommendation {

    private Long _id;
    private String _location;
    private String _title;
    private String _description;
    private double _targetLat;
    private double _targetLong;
    private String _street;
    private String _city;
    private String _country;
    private String _image;
    private boolean _visible;
    private boolean _active;
    private Date _dateInactive;

    EditedRecommendation(){}

    public EditedRecommendation(Long _id, String _location, String _title, String _description, double _targetLat, double _targetLong, String _street, String _city, String _country, String _image, boolean _visible, boolean _active, Date _dateInactive) {
        this._id = _id;
        this._location = _location;
        this._title = _title;
        this._description = _description;
        this._targetLat = _targetLat;
        this._targetLong = _targetLong;
        this._street = _street;
        this._city = _city;
        this._country = _country;
        this._image = _image;
        this._visible = _visible;
        this._active = _active;
        this._dateInactive = _dateInactive;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

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

    public boolean is_visible() {
        return _visible;
    }

    public void set_visible(boolean _visible) {
        this._visible = _visible;
    }

    public boolean is_active() {
        return _active;
    }

    public void set_active(boolean _active) {
        this._active = _active;
    }

    public Date get_dateInactive() {
        return _dateInactive;
    }

    public void set_dateInactive(Date _dateInactive) {
        this._dateInactive = _dateInactive;
    }
}
