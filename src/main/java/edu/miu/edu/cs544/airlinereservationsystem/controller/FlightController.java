package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Flight AddFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Flight> getAllAirlines(){
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Flight> getById(@PathVariable("id") Long id){
        return flightService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flight updateAirline(@PathVariable("id") Long id, @RequestBody Flight flight){
        return flightService.updateFlight(id, flight);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFlight(@PathVariable("id") Long id){
        flightService.deleteFlight(id);
    }

    @GetMapping("/flightTrips")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Flight> flightTrips(@RequestBody FlightTripRequest flightTripRequest){
        return flightService.flightTrips(flightTripRequest);
    }

}
