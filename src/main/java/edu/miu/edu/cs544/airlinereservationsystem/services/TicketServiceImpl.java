package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.TicketRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketsRepository;

    @Override
    public List<Ticket> findAll() {
        return ticketsRepository.findAll();
    }

    @Override
    public Ticket findOne(Long i) {
        return ticketsRepository.findById(i).orElse(null);
    }

    @Override
    public Ticket update(Ticket t) {
        return  ticketsRepository.save(t);
    }

    @Override
    public void delete(Long i) {

        ticketsRepository.deleteById(i);
    }

    @Override
    public Ticket add(Ticket t) {
        return  ticketsRepository.save(t);
    }
}
