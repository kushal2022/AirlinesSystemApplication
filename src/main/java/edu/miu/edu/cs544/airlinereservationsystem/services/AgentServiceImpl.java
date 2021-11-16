package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AgentRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.ReservationRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Agent;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Passenger;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AgentRepository agentRepository;

    @Override
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }


    @Override
    public Agent addAgent(Agent passenger) {
        return agentRepository.save(passenger);
    }

    @Override
    public Agent getAgent(Long id) {
        return agentRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAgent(Long id, Agent passenger) {
        Optional<Agent> optionalPassenger = agentRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            Agent oldPassenger = optionalPassenger.get();
            oldPassenger.setFirstName(passenger.getFirstName());
            oldPassenger.setLastName(passenger.getLastName());
            oldPassenger.setDob(passenger.getDob());
            oldPassenger.setEmail(passenger.getEmail());
            oldPassenger.setUsername(passenger.getUsername());
            oldPassenger.setPassword(passenger.getPassword());
            agentRepository.save(oldPassenger);
        }
    }

    @Override
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }
}
