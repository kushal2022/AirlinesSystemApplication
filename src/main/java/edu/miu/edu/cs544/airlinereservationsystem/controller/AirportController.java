package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Airport AddAirport(@RequestBody Airport airport){
        return airportService.addAirport(airport);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Airport> getAllAirport(){
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Airport> getById(@PathVariable("id") Long id){
        return airportService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Airport updateAirline(@PathVariable("id") Long id, @RequestBody Airport airport){
        return airportService.updateAirport(id, airport);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAirport(@PathVariable("id") Long id){
        airportService.deleteAirPort(id);
    }


}
