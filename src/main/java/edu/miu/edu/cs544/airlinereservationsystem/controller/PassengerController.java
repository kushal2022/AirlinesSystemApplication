package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @RequestMapping(value = "/{id}/reservations", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<List<Reservation>> getMyReservation(@PathVariable Long id) {
        validatePassenger(id);
        List<Reservation> reservations = passengerService.getMyReservation(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    private ResponseEntity validatePassenger(Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String passengerUsername = authentication.getName();
            Passenger passenger = passengerService.findByUsername(passengerUsername);
            if (passenger.getId() != id) {
                return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> passengers = passengerService.getPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        return new ResponseEntity<>(passengerService.addPassenger(passenger), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassenger(id);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        passengerService.updatePassenger(id, passenger);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
