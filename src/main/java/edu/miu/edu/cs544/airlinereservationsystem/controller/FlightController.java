package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Flight;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.FlightTripRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> AddFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(newFlight.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<List<Flight>> getAllAirlines(){
        List<Flight> flights = flightService.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Flight> getById(@PathVariable("id") Long id){
        Flight flight = flightService.findById(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> updateAirline(@PathVariable("id") Long id, @RequestBody FlightRequest flight){
        Flight updatedFlight = flightService.updateFlight(id, flight);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>  deleteFlight(@PathVariable("id") Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/flightTrips", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<List<Flight>> flightTrips(@RequestBody FlightTripRequest flightTripRequest){
        List<Flight> flights = flightService.flightTrips(flightTripRequest);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
