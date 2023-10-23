package com.example.fms.service;

import com.example.fms.entity.Airport;
import com.example.fms.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // Create a new airport
    public Airport createAirport(Airport airport) {
        if (airport.getName() == null || airport.getName().isEmpty()) {
            throw new IllegalArgumentException("Airport name cannot be empty.");
        }
        return airportRepository.save(airport);
    }

    // Read airport details
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    // Update airport details
    public Airport updateAirport(Long airportId, Airport updatedAirport) {
        Optional<Airport> airportOptional = airportRepository.findById(airportId);
        if (airportOptional.isEmpty()) {
            throw new IllegalStateException("Airport with ID " + airportId + " does not exist");
        }

        Airport existingAirport = airportOptional.get();
        // Update the properties of the existingAirport with values from updatedAirport
        existingAirport.setName(updatedAirport.getName());
        existingAirport.setCode(updatedAirport.getCode());

        return airportRepository.save(existingAirport);
    }

    // Delete an airport
    public void deleteAirport(Long airportId) {
        boolean exists = airportRepository.existsById(airportId);
        if (!exists) {
            throw new IllegalStateException("Airport with ID " + airportId + " does not exist");
        }
        airportRepository.deleteById(airportId);
    }
}
