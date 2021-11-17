package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirlineRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @PostMapping
    public ResponseEntity<Airline> AddAirline(@Validated @RequestBody AirlineRequest airlineRequest){
        Airline airline = airlineService.addAirline(airlineRequest);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Airline>>  getAllAirlines(){
        List<Airline> airlines = airlineService.findAll();
        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }

    @RequestMapping(value = "/airport/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Airline>> getAirlinesFlyingFromAirportX(@PathVariable Long id) {
        List<Airline> airlines = airlineService.getAirlinesFlyingFromAirportX(id);
        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Airline> getById(@PathVariable("id") Long id){
        Airline airline = airlineService.findById(id);
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Airline> updateAirline(@PathVariable("id") Long id, @Validated @RequestBody AirlineRequest airlineRequest){
        Airline airline = airlineService.updateAirline(id, airlineRequest);
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAirline(@PathVariable("id") Long id){
        airlineService.deleteAirline(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
