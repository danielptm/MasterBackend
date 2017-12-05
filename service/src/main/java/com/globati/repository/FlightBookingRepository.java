package com.globati.repository;

import com.globati.dbmodel.FlightBooking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightBookingRepository extends CrudRepository<FlightBooking, Long> {

    List<FlightBooking> getFlightBookingByEmployeeId(Long id);

}
