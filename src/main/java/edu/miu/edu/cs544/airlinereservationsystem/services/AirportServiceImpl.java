package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AirportRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airline;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.Airport;
import edu.miu.edu.cs544.airlinereservationsystem.model.AirportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository repository;

    @Override
    public Airport addAirport(AirportRequest airportRequest) {
        Airport airport = new Airport();
        airport.setName(airportRequest.getName());
        airport.setCode(airportRequest.getCode());
        airport.setAddress(airportRequest.getAddress());
        return repository.save(airport);
    }

    @Override
    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Airport findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Airport updateAirport(Long id, AirportRequest airportRequest) {
        Optional<Airport> entity = repository.findById(id);
        if(entity.isPresent()){
            Airport airport = entity.get();
            airport.setName(airportRequest.getName());
            airport.setCode(airportRequest.getCode());
            airport.setAddress(airportRequest.getAddress());
            return repository.save(airport);
        }
        return null;

    }
    @Override
    public void deleteAirPort(Long id) {
        repository.deleteById(id);
    }
}
