package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.model.ReservationRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Long> createReservation(@Validated @RequestBody ReservationRequest reservationRequest) {
        Reservation savedReservation = service.saveReservation(reservationRequest);
        return new ResponseEntity<>(savedReservation.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Reservation> getReservationDetails(@PathVariable Long id) {
        Reservation reservation = service.getReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Void> updateReservation(@PathVariable Long id, @Validated @RequestBody ReservationRequest reservationRequest) {
        service.updateReservation(id, reservationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Void> confirmReservation(@PathVariable Long id) {
        service.confirmReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('PASSENGER') OR hasRole('AGENT') OR hasRole('ADMIN')")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        service.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
