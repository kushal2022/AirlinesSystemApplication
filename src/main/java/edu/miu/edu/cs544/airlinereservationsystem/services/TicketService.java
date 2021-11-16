package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Ticket;
import edu.miu.edu.cs544.airlinereservationsystem.model.TicketRequest;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();
    Ticket findTicketById(Long id);
    Ticket update(Long id, TicketRequest ticketRequest);
    void delete (Long id);

    void createTicket(TicketRequest ticketRequest);
}
