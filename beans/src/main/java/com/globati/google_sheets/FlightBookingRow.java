package com.globati.google_sheets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FlightBookingRow {

    Long employeeId;
    String timeBooked;
    String paidStatus;
    Double costOfTicket;
    Double globatiCommission;
    String flightPlan;
    Integer numberOfPeople;
    Date departureDate;
    Date returnDate;
    String globatiMarker;
    String companyBookedWith;

    public FlightBookingRow(String timeBooked, String paidStatus, String costOfTicket, String globatiCommission, String flightPlan, String numberOfPeople, String departureDate, String returnDate, String globatiMarker, String companyBookedWith) {
        this.timeBooked = timeBooked;
        this.paidStatus = paidStatus;
        this.costOfTicket = adaptCost(costOfTicket);
        this.globatiCommission = adaptCost(globatiCommission);
        this.flightPlan = flightPlan;
        this.numberOfPeople = adaptNumber(numberOfPeople);
        this.departureDate = adaptDate(departureDate);
        this.returnDate = adaptDate(returnDate);
        this.globatiMarker = globatiMarker;
        this.companyBookedWith = companyBookedWith;
    }

    private Integer adaptNumber(String marker){
        return Integer.parseInt(marker);
    }

    private Double adaptCost(String cost){
        String adjustedCost = cost.substring(1);
        return Double.parseDouble(adjustedCost);
    }

    private Date adaptDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate parsedDate = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(parsedDate);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public Double getCostOfTicket() {
        return costOfTicket;
    }

    public Double getGlobatiCommission() {
        return globatiCommission;
    }

    public String getFlightPlan() {
        return flightPlan;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getGlobatiMarker() {
        return globatiMarker;
    }

    public String getCompanyBookedWith() {
        return companyBookedWith;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightBookingRow{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", timeBooked='").append(timeBooked).append('\'');
        sb.append(", paidStatus='").append(paidStatus).append('\'');
        sb.append(", costOfTicket=").append(costOfTicket);
        sb.append(", globatiCommission=").append(globatiCommission);
        sb.append(", flightPlan='").append(flightPlan).append('\'');
        sb.append(", numberOfPeople=").append(numberOfPeople);
        sb.append(", departureDate=").append(departureDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", globatiMarker='").append(globatiMarker).append('\'');
        sb.append(", companyBoojkedWith='").append(companyBookedWith).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
