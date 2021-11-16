package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AirlineRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirlineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository repository;

    @Override
    public Airline addAirline(AirlineRequest airlineRequest) {

        Airline airline = new Airline();
        airline.setCode(airlineRequest.getCode());
        airline.setName(airlineRequest.getName());
        airline.setHistory(airlineRequest.getHistory());
        return repository.save(airline);
    }

    @Override
    public List<Airline> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Airline updateAirline(Long id, AirlineRequest airlineRequest) {
        Optional<Airline> entity = repository.findById(id);
        if(entity.isPresent()){
            Airline airline = entity.get();
            airline.setName(airlineRequest.getName());
            airline.setCode(airlineRequest.getCode());
            airline.setHistory(airlineRequest.getHistory());
            return repository.save(airline);
        }
        return null;
    }

    @Override
    public void deleteAirline(Long id) {
        repository.deleteById(id);
    }
}
