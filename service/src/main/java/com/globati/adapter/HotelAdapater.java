package com.globati.adapter;

import com.globati.dbmodel.FlightBooking;
import com.globati.dbmodel.HotelBooking;
import com.globati.deserialization_beans.response.employee.ResponseHotel;
import org.springframework.stereotype.Service;

@Service
public class HotelAdapater {

    public ResponseHotel getAndTranslateAHotel(HotelBooking booking){

        ResponseHotel hotel = new ResponseHotel(
                booking.getTimeBooked(),
                booking.getDateBooked().toString(),
                booking.getHotelCity(),
                booking.getEmployeeComission().toString()
        );

        return hotel;

    }
}
