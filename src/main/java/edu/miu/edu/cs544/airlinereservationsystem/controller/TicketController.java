package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Ticket;
import edu.miu.edu.cs544.airlinereservationsystem.model.TicketRequest;
import edu.miu.edu.cs544.airlinereservationsystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketsService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> createTicket(@RequestBody TicketRequest ticketRequest) {
        ticketsService.createTicket(ticketRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> ticketList = ticketsService.findAll();
        if (ticketList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticketList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long id) {
        Ticket ticket = ticketsService.findTicketById(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, @RequestBody @Valid TicketRequest ticketRequest) {
        Ticket updatedTicket = ticketsService.update(id, ticketRequest);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable long id) {
        ticketsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
