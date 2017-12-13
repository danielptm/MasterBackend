package com.globati.repository;

import com.globati.dbmodel.FlightBooking;
import com.globati.enums.TicketPaidStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightBookingRepository extends CrudRepository<FlightBooking, Long> {

    List<FlightBooking> getFlightBookingByEmployeeId(Long id);

//    @Query("SELECT e FROM Employee e WHERE e.id =:id " )

    @Query("SELECT f FROM FlightBooking  f WHERE f.employee.id=:id AND f.paidStatus=:paidStatus")
    List<FlightBooking> getFlightBookingByEmployeeIdAndPaidStatus(@Param("id") Long id, @Param("paidStatus") TicketPaidStatus paidStatus);

}
