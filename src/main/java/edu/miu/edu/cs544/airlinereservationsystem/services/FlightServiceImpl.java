package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.FlightRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository repository;

    @Override
    public Flight addFlight(FlightRequest flightRequest) {

        Flight flight = new Flight();
        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setArrivalTime(flightRequest.getArrivalTime());
        flight.setCapacity(flightRequest.getCapacity());
        flight.setAirline(flightRequest.getAirline());
        flight.setArrivalAirport(flightRequest.getArrivalAirport());
        flight.setDepartureAirport(flightRequest.getDepartureAirport());
        flight.setNumber(flightRequest.getNumber());
        return repository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flight updateFlight(Long id, FlightRequest flightRequest) {
        Optional<Flight> entity = repository.findById(id);
        if(entity.isPresent()){
            Flight flight1 = entity.get();
            flight1.setNumber(flightRequest.getNumber());
            flight1.setCapacity(flightRequest.getCapacity());
            flight1.setAirline(flightRequest.getAirline());
            flight1.setDepartureAirport(flightRequest.getDepartureAirport());
            flight1.setArrivalAirport(flightRequest.getArrivalAirport());
            flight1.setDepartureTime(flightRequest.getDepartureTime());
            flight1.setArrivalTime(flightRequest.getArrivalTime());
            return repository.save(flight1);
        }
        return null;
    }

    @Override
    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }

    @Override

    public List<Flight> flightTrips(FlightTripRequest flightTripRequest) {
        return repository.getFlightByDateAndAirport(flightTripRequest.getDepartureAirport().getId(),
                flightTripRequest.getArrivalAirport().getId(), flightTripRequest.getFlightDate());
    }
}