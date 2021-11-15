package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AirlineRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository repository;

    @Override
    public Airline addAirline(Airline airline) {
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
    public Airline updateAirline(Long id, Airline airline) {
        Optional<Airline> entity = repository.findById(id);
//        entity.get().setId(airline.getId());
        entity.get().setName(airline.getName());
        entity.get().setCode(airline.getCode());
        entity.get().setHistory(airline.getHistory());
        return repository.save(airline);
    }

    @Override
    public void deleteAirline(Long id) {
        repository.deleteById(id);
    }
}
