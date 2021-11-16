package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Agent;

import java.util.List;

public interface AgentService {

    Agent addAgent(Agent passenger);
    List<Agent> getPassengers();
    Agent getAgent(Long id);
    void updatePassenger(Long id, Agent passenger);
    void deletePassenger(Long id);
}
