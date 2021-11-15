package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import lombok.Data;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    Flight addFlight(Flight flight);
    List<Flight> findAll();
    Optional<Flight> findById(Long id);
    Flight updateFlight(Long id, Flight flight);
    void deleteFlight(Long id);
    List<Flight> flightTrips(Airport arrivalAirport, Airport departureAirport);

}
