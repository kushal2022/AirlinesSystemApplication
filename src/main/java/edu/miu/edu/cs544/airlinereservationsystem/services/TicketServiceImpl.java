package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.TicketRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Ticket;
import edu.miu.edu.cs544.airlinereservationsystem.model.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketsRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void createTicket(TicketRequest ticketRequest) {

        Reservation reservation = reservationRepository.getById(ticketRequest.getReservation().getId());

        Ticket ticket = new Ticket();
        ticket.setNumber(ticketRequest.getNumber());
        ticket.setFlightDate(ticketRequest.getFlightDate());
        ticket.setReservation(reservation);
        ticket.setFlight(ticket.getFlight());
        ticketsRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketsRepository.findAll();
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketsRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket update(Long id, TicketRequest ticketRequest) {

        Optional<Ticket> optionalTicket = ticketsRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket oldTicket = optionalTicket.get();
            oldTicket.setNumber(ticketRequest.getNumber());
            oldTicket.setFlightDate(ticketRequest.getFlightDate());
            oldTicket.setReservation(ticketRequest.getReservation());
            return ticketsRepository.save(oldTicket);
        }
        return null;
    }

    @Override
    public void delete(Long i) {
        ticketsRepository.deleteById(i);
    }
}
