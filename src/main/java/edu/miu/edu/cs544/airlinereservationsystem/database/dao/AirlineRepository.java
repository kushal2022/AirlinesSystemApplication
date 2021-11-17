package edu.miu.edu.cs544.airlinereservationsystem.database.dao;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Query(value = "SELECT * FROM Flight f JOIN Airline a on f.airline_id = a.id JOIN History h on h.id = a.id WHERE departureAirport_id = ?1", nativeQuery = true)
    List<Airline> getAirlinesFlyingFromAirportX(Long airportId);

}
