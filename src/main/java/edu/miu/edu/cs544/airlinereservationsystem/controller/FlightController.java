package edu.miu.edu.cs544.airlinereservationsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping
    public String hello() {
        return "Welcome here!";
    }
}
