package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    Airport addAirport(Airport airport);
    List<Airport> findAll();
    Optional<Airport> findById(Long id);
    Airport updateAirport(Long id, Airport airport);
    void deleteAirPort(Long id);
}
