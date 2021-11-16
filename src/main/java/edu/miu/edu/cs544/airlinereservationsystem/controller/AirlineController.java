package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Airline AddAirline(@RequestBody Airline airline){
        return airlineService.addAirline(airline);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Airline> getAllAirlines(){
        return airlineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Airline> getById(@PathVariable("id") Long id){
        return airlineService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Airline updateAirline(@PathVariable("id") Long id, @RequestBody Airline airline){
        return airlineService.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAirline(@PathVariable("id") Long id){
        airlineService.deleteAirline(id);
    }
}
