package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name="event")
public class Event extends BusinessEntity{

    @Column(name="date")
    private Date date;
    @Column(length=100, name="repeating") //this has to be repeating, i think repeat is a reserved word in mysql, and while works on derby does not for mysql
    private String repeat;
    @Column(name="publicvisible")
    private boolean publicVisible;

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    Employee employee;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    List<EventImage> eventimages;

    public Event(){}

    //Took away distance, because this value will vary depending on the receptionists location
    public Event(Employee employee, Date date, double targetLat, double targetLong, String street, String city, String country, String title, String description, String imageName1, String imageName2, String imageName3) {
        this.employee = employee;
        this.date = date;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.repeat = repeat;
        this.street = street;
        this.city = city;
        this.country = country;
        this.description = description;
        this.active = true;
        this.title = title;
        this.image = imageName1;
        this.image2 = imageName2;
        this.image3 = imageName3;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public boolean isPublicVisible() {
        return publicVisible;
    }

    public void setPublicVisible(boolean publicVisible) {
        this.publicVisible = publicVisible;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<EventImage> getEventimages() {
        return eventimages;
    }

    public void setEventimages(List<EventImage> eventimages) {
        this.eventimages = eventimages;
    }


}
