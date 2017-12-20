package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.GlobatiPaymentStatus;
import com.globati.enums.TicketPaidStatus;

import javax.persistence.*;
import java.util.Date;

public class HotelBooking extends BookingEntity{
    @Column(name="datebooked")
    Date dateBooked;
    @Column(name = "timebooked")
    String timeBooked;
    @Column(name = "paidstatus")
    @Enumerated(EnumType.STRING)
    TicketPaidStatus paidStatus;
    @Column(name = "costofticket")
    Double costOfTicket;
    @Column(name = "globaticomission")
    Double globatiCommission;
    @Column(name = "employeecomission")
    Double employeeComission;
    @Column(name = "hotelcity")
    String hotelCity;
    @Column(name = "checkindate")
    Date checkinDate;
    @Column(name = "checkoutdate")
    Date getCheckOutDate;
    @Column(name = "globatimarker")
    String globatiMarker;
    @Column(name = "companybookedwith")
    String companyBookedWith;

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    private Employee employee;

    public HotelBooking(Date dateBooked, String timeBooked, TicketPaidStatus paidStatus, Double costOfTicket, Double globatiCommission, Double employeeComission, String hotelCity, Date checkinDate, Date getCheckOutDate, String globatiMarker, String companyBookedWith, Employee employee) {
        this.dateBooked = dateBooked;
        this.timeBooked = timeBooked;
        this.paidStatus = paidStatus;
        this.costOfTicket = costOfTicket;
        this.globatiCommission = globatiCommission;
        this.employeeComission = employeeComission;
        this.hotelCity = hotelCity;
        this.checkinDate = checkinDate;
        this.getCheckOutDate = getCheckOutDate;
        this.globatiMarker = globatiMarker;
        this.companyBookedWith = companyBookedWith;
        this.employee = employee;
        this.globatiPaymentStatus = GlobatiPaymentStatus.NOT_PAID;

    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
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

    public Double getEmployeeComission() {
        return employeeComission;
    }

    public void setEmployeeComission(Double employeeComission) {
        this.employeeComission = employeeComission;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getGetCheckOutDate() {
        return getCheckOutDate;
    }

    public void setGetCheckOutDate(Date getCheckOutDate) {
        this.getCheckOutDate = getCheckOutDate;
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
        final StringBuilder sb = new StringBuilder("HotelBooking{");
        sb.append("dateBooked=").append(dateBooked);
        sb.append(", timeBooked='").append(timeBooked).append('\'');
        sb.append(", paidStatus=").append(paidStatus);
        sb.append(", costOfTicket=").append(costOfTicket);
        sb.append(", globatiCommission=").append(globatiCommission);
        sb.append(", employeeComission=").append(employeeComission);
        sb.append(", hotelCity='").append(hotelCity).append('\'');
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", getCheckOutDate=").append(getCheckOutDate);
        sb.append(", globatiMarker='").append(globatiMarker).append('\'');
        sb.append(", companyBookedWith='").append(companyBookedWith).append('\'');
        sb.append(", employee=").append(employee);
        sb.append(", globatiPaymentStatus=").append(globatiPaymentStatus);
        sb.append('}');
        return sb.toString();
    }
}
