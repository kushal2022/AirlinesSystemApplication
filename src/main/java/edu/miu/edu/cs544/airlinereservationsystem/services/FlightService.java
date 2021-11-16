package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;
import lombok.Data;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    Flight addFlight(FlightRequest flightRequest);
    List<Flight> findAll();
    Optional<Flight> findById(Long id);
    Flight updateFlight(Long id, FlightRequest flightRequest);
    void deleteFlight(Long id);
    List<Flight> flightTrips(FlightTripRequest flightTripRequest);

}
