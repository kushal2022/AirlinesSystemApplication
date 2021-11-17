package edu.miu.edu.cs544.airlinereservationsystem.controller;

import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Agent;
import edu.miu.edu.cs544.airlinereservationsystem.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/agents")
public class AgentController {

    @Autowired
    AgentService agentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Agent>> getAgents() {
        List<Agent> passengers = agentService.getAgents();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        return new ResponseEntity<>(agentService.addAgent(agent), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agent> getAgent(@PathVariable Long id) {
        Agent agent = agentService.getAgent(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAgent(@PathVariable Long id, @RequestBody Agent agent) {
        agentService.updateAgent(id, agent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
