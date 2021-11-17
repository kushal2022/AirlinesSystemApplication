package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.FlightRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository repository;

//    @Override
//    public Flight addFlight(FlightRequest flightRequest) {
//
//        Flight flight = new Flight();
//        flight.setDepartureTime(flightRequest.getDepartureTime());
//        flight.setArrivalTime(flightRequest.getArrivalTime());
//        flight.setCapacity(flightRequest.getCapacity());
//
//        Long airlineId = flightRequest.getAirlineId();
//        Airline airline = new Airline();
//        airline.setId(airlineId);
//        flight.setAirline(airline);
//
//        Long arrivalAirportId = flightRequest.getArrivalAirportId();
//        Airport arrivalAirport = new Airport();
//        arrivalAirport.setId(arrivalAirportId);
//        flight.setArrivalAirport(arrivalAirport);
//
//        Long departureAirportId = flightRequest.getDepartureAirportId();
//        Airport departureAirport = new Airport();
//        departureAirport.setId(departureAirportId);
//        flight.setDepartureAirport(departureAirport);
//
//
////        flight.setArrivalAirport(new Airport(flightRequest.getArrivalAirportId()));
////        flight.setDepartureAirport(new Airport(flightRequest.getArrivalAirportId()));
//
//        flight.setNumber(flightRequest.getNumber());
//        return repository.save(flight);
//    }

    @Override
    public Flight addFlight(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Flight updateFlight(Long id, FlightRequest flightRequest) {
        Optional<Flight> entity = repository.findById(id);

        if(entity.isPresent()){
            Flight flight1 = entity.get();
            flight1.setNumber(flightRequest.getNumber());
            flight1.setCapacity(flightRequest.getCapacity());

            Long airlineId = flightRequest.getAirlineId();
            Airline airline = new Airline();
            airline.setId(airlineId);
            flight1.setAirline(airline);

            Long arrivalAirportId = flightRequest.getArrivalAirportId();
            Airport arrivalAirport = new Airport();
            arrivalAirport.setId(arrivalAirportId);
            flight1.setArrivalAirport(arrivalAirport);

            Long departureAirportId = flightRequest.getDepartureAirportId();
            Airport departureAirport = new Airport();
            departureAirport.setId(departureAirportId);
            flight1.setDepartureAirport(departureAirport);

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
        Long departureAirportId = flightTripRequest.getDepartureAirport().getId();
        Long arrivalAirportId = flightTripRequest.getArrivalAirport().getId();
        LocalDate flightDate = flightTripRequest.getFlightDate();
        return repository.getFlightByDateAndAirport(departureAirportId, arrivalAirportId, flightDate);
    }
}