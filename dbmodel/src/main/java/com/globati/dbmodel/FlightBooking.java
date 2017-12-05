package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.TicketPaidStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flightbooking")
public class FlightBooking extends BookingEntity{


    @Column(name = "timebooked")
    String timeBooked;
    @Column(name = "paidstatus")
    @Enumerated(EnumType.STRING)
    TicketPaidStatus paidStatus;
    @Column(name = "costofticket")
    Double costOfTicket;
    @Column(name = "globaticomission")
    Double globatiCommission;
    @Column(name = "flightplan")
    String flightPlan;
    @Column(name = "numberofpeople")
    Integer numberOfPeople;
    @Column(name = "departuredate")
    Date departureDate;
    @Column(name = "returndate")
    Date returnDate;
    @Column(name = "globatimarker")
    String globatiMarker;
    @Column(name = "companybookedwith")
    String companyBookedWith;

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    private Employee employee;

    public FlightBooking() {}

    public FlightBooking(String timeBooked, TicketPaidStatus paidStatus, Double costOfTicket, Double globatiCommission, String flightPlan, Integer numberOfPeople, Date departureDate, Date returnDate, String globatiMarker, String companyBookedWith, Employee employee) {
        this.timeBooked = timeBooked;
        this.paidStatus = paidStatus;
        this.costOfTicket = costOfTicket;
        this.globatiCommission = globatiCommission;
        this.flightPlan = flightPlan;
        this.numberOfPeople = numberOfPeople;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.globatiMarker = globatiMarker;
        this.companyBookedWith = companyBookedWith;
        this.employee = employee;
    }



    public String getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public TicketPaidStatus getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(TicketPaidStatus paidStatus) {
        this.paidStatus = paidStatus;
    }

    public Double getCostOfTicket() {
        return costOfTicket;
    }

    public void setCostOfTicket(Double costOfTicket) {
        this.costOfTicket = costOfTicket;
    }

    public Double getGlobatiCommission() {
        return globatiCommission;
    }

    public void setGlobatiCommission(Double globatiCommission) {
        this.globatiCommission = globatiCommission;
    }

    public String getFlightPlan() {
        return flightPlan;
    }

    public void setFlightPlan(String flightPlan) {
        this.flightPlan = flightPlan;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getGlobatiMarker() {
        return globatiMarker;
    }

    public void setGlobatiMarker(String globatiMarker) {
        this.globatiMarker = globatiMarker;
    }

    public String getCompanyBookedWith() {
        return companyBookedWith;
    }

    public void setCompanyBookedWith(String companyBookedWith) {
        this.companyBookedWith = companyBookedWith;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightBooking{");
        sb.append(", timeBooked='").append(timeBooked).append('\'');
        sb.append(", paidStatus=").append(paidStatus);
        sb.append(", costOfTicket=").append(costOfTicket);
        sb.append(", globatiCommission=").append(globatiCommission);
        sb.append(", flightPlan='").append(flightPlan).append('\'');
        sb.append(", numberOfPeople=").append(numberOfPeople);
        sb.append(", departureDate=").append(departureDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", globatiMarker='").append(globatiMarker).append('\'');
        sb.append(", companyBookedWith='").append(companyBookedWith).append('\'');
        sb.append(", employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }

}
