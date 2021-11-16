package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    @Query(value = "SELECT DISTINCT * FROM Flight f join Reservation_Flight rf ON f.id = rf.flight_id " +
            "JOIN Reservation r ON r.id = rf.Reservation_id " +
            "WHERE arrivalAirport_id = ?1 and departureAirport_id = ?2 and r.flightDate = ?3")
    List<Flight> getFlightByDateAndAirport(Long departureAirportId, Long arrivalAirportId, LocalDate date);
    
}
