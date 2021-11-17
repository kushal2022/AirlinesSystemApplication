package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirportRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airport> AddAirport(@Validated @RequestBody AirportRequest airportRequest) {
        Airport airport = airportService.addAirport(airportRequest);
        return new ResponseEntity<>(airport, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<List<Airport>> getAllAirport() {
        List<Airport> airports = airportService.findAll();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Airport> getById(@PathVariable("id") Long id) {
        Airport airport = airportService.findById(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airport> updateAirline(@PathVariable("id") Long id, @Validated @RequestBody AirportRequest airportRequest) {
        Airport airport = airportService.updateAirport(id, airportRequest);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAirport(@PathVariable("id") Long id) {
        airportService.deleteAirPort(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
