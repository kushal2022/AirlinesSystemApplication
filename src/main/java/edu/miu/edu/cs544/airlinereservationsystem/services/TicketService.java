package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> findAll();
    public Ticket findOne(Long id);
    public Ticket update(Ticket ticket);
    public void delete (Long id);
    public Ticket add(Ticket t);
}
