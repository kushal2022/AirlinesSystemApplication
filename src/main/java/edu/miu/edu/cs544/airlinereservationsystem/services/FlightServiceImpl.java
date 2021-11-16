package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.FlightRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
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
    public Flight addFlight(Flight flight) {
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
    public Flight updateFlight(Long id, Flight flight) {
        Optional<Flight> entity = repository.findById(id);
//        entity.get().setId(flight.getId());
        entity.get().setNumber(flight.getNumber());
        entity.get().setCapacity(flight.getCapacity());
        entity.get().setAirline(flight.getAirline());
        entity.get().setDepartureAirport(flight.getDepartureAirport());
        entity.get().setArrivalAirport(flight.getArrivalAirport());
        entity.get().setDepartureTime(flight.getDepartureTime());
        entity.get().setArrivalTime(flight.getArrivalTime());
        return repository.save(entity.get());

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