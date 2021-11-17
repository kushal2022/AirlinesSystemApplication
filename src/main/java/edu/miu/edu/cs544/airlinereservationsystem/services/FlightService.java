package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;

import java.util.List;

public interface FlightService {
    Flight addFlight(FlightRequest flightRequest);
    List<Flight> findAll();
    Flight findById(Long id);
    Flight updateFlight(Long id, FlightRequest flightRequest);
    void deleteFlight(Long id);
    List<Flight> flightTrips(FlightTripRequest flightTripRequest);

}
