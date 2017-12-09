package com.globati.adapter;

import com.globati.dbmodel.FlightBooking;
import com.globati.deserialization_beans.response.employee.ResponseFlight;
import org.springframework.stereotype.Service;

@Service
public class FlightAdapater {

    public ResponseFlight getAndTranslateAflight(FlightBooking booking){

        ResponseFlight flight = new ResponseFlight(
                booking.getTimeBooked(),
                booking.getDateBooked().toString(),
                booking.getFlightPlan(),
                booking.getEmployeeComission().toString()
            );

        return flight;

    }
}
