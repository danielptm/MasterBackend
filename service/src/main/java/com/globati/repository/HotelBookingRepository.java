package com.globati.repository;

import com.globati.dbmodel.FlightBooking;
import com.globati.dbmodel.HotelBooking;
import com.globati.enums.GlobatiPaymentStatus;
import com.globati.enums.TicketPaidStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelBookingRepository extends CrudRepository<HotelBooking, Long>{

    List<HotelBooking> getHotelBookingByEmployeeId(Long id);

//    @Query("SELECT e FROM Employee e WHERE e.id =:id " )

    @Query("SELECT h FROM HotelBooking h WHERE h.employee.id=:id AND h.paidStatus=:paidStatus AND h.globatiPaymentStatus=:globatiPaidStatus")
    List<HotelBooking> getFlightBookingByEmployeeIdAndPaidStatus(
            @Param("id") Long id,
            @Param("paidStatus") TicketPaidStatus paidStatus,
            @Param("globatiPaidStatus") GlobatiPaymentStatus globatiPaidStatus);
}
