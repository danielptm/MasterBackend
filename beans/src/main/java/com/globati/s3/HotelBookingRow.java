package com.globati.s3;

import com.globati.dbmodel.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//TODO 12/20/17 DRY principle. This is repeating itselt with FlightBookingRow a bit. Create a super class BookingRow that these can inherit from.
public class HotelBookingRow {

    Long employeeId;
    String timeBooked;
    String paidStatus;
    Double costOfTicket;
    Double globatiComission;
    String flightPlan;
    Date departureDate;
    Date returnDate;
    String globatiMaker;
    String companyBookedWith;
    Date dateBooked;

    public HotelBookingRow(String timeBooked, String paidStatus, String costOfTicket, String globatiComission, String flightPlan, String departureDate, String returnDate, String globatiMaker, String companyBookedWith, String dateBooked) {
        this.timeBooked = timeBooked;
        this.paidStatus = paidStatus;
        this.costOfTicket = adaptCost(costOfTicket);
        this.globatiComission = adaptCost(globatiComission);
        this.flightPlan = flightPlan;
        this.departureDate = adaptDate(departureDate);
        this.returnDate = adaptDate(returnDate);
        this.globatiMaker = globatiMaker;
        this.companyBookedWith = companyBookedWith;
        this.dateBooked = adaptBookingDate(dateBooked);
    }


    private Double adaptCost(String cost){
        return Double.parseDouble(cost);
    }

    private Date adaptDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate parsedDate = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(parsedDate);
    }

    private Date adaptBookingDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(parsedDate);
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public Double getCostOfTicket() {
        return costOfTicket;
    }

    public void setCostOfTicket(Double costOfTicket) {
        this.costOfTicket = costOfTicket;
    }

    public Double getGlobatiComission() {
        return globatiComission;
    }

    public void setGlobatiComission(Double globatiComission) {
        this.globatiComission = globatiComission;
    }

    public String getFlightPlan() {
        return flightPlan;
    }

    public void setFlightPlan(String flightPlan) {
        this.flightPlan = flightPlan;
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

    public String getGlobatiMaker() {
        return globatiMaker;
    }

    public void setGlobatiMaker(String globatiMaker) {
        this.globatiMaker = globatiMaker;
    }

    public String getCompanyBookedWith() {
        return companyBookedWith;
    }

    public void setCompanyBookedWith(String companyBookedWith) {
        this.companyBookedWith = companyBookedWith;
    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HotelBookingRow{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", timeBooked='").append(timeBooked).append('\'');
        sb.append(", paidStatus='").append(paidStatus).append('\'');
        sb.append(", costOfTicket=").append(costOfTicket);
        sb.append(", globatiComission=").append(globatiComission);
        sb.append(", flightPlan='").append(flightPlan).append('\'');
        sb.append(", departureDate=").append(departureDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", globatiMaker='").append(globatiMaker).append('\'');
        sb.append(", companyBookedWith='").append(companyBookedWith).append('\'');
        sb.append(", dateBooked=").append(dateBooked);
        sb.append('}');
        return sb.toString();
    }
}
