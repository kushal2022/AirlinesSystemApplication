package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirlineRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    public Airline AddAirline(@Validated @RequestBody AirlineRequest airlineRequest){
        return airlineService.addAirline(airlineRequest);
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
    public Airline updateAirline(@PathVariable("id") Long id, @Validated @RequestBody AirlineRequest airlineRequest){
        return airlineService.updateAirline(id, airlineRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAirline(@PathVariable("id") Long id){
        airlineService.deleteAirline(id);
    }
}
