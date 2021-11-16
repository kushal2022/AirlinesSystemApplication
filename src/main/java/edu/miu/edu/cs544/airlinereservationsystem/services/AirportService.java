package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirportRequest;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    Airport addAirport(AirportRequest airportRequest);
    List<Airport> findAll();
    Optional<Airport> findById(Long id);
    Airport updateAirport(Long id, AirportRequest airportRequest);
    void deleteAirPort(Long id);
}
