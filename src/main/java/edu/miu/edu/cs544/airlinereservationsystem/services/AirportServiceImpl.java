package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AirportRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository repository;

    @Override
    public Airport addAirport(Airport airport) {
        return repository.save(airport);
    }

    @Override
    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Airport updateAirport(Long id, Airport airport) {
        Optional<Airport> entity = repository.findById(id);
//        entity.get().setId(airport.getId());
        entity.get().setName(airport.getName());
        entity.get().setCode(airport.getCode());
        entity.get().setAddress(airport.getAddress());
        return repository.save(entity.get());
    }

    @Override
    public void deleteAirPort(Long id) {
        repository.deleteById(id);
    }
}
