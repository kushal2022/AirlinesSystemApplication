package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    Airline addAirline(Airline airline);
    List<Airline> findAll();
    Optional<Airline> findById(Long id);
    Airline updateAirline(Long id, Airline airline);
    void deleteAirline(Long id);
}
