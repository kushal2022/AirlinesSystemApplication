package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirlineRequest;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    Airline addAirline(AirlineRequest airlineRequest);
    List<Airline> findAll();
    List<Airline> getAirlinesFlyingFromAirportX(Long airportId);
    Airline findById(Long id);
    Airline updateAirline(Long id, AirlineRequest airlineRequest);
    void deleteAirline(Long id);
}
