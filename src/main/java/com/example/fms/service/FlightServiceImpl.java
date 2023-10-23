package com.example.fms.service;

import com.example.fms.entity.Flight;
import com.example.fms.entity.FlightStatus;
import com.example.fms.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        // Set the initial status to "Scheduled" when creating a new flight
        flight.setStatus(FlightStatus.SCHEDULED);
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isEmpty()) {
            throw new IllegalStateException("Flight with ID " + flightId + " does not exist");
        }
        return flightOptional.get();
    }

    @Override
    public Flight updateFlight(Long flightId, Flight updatedFlight) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isEmpty()) {
            throw new IllegalStateException("Flight with ID " + flightId + " does not exist");
        }

        Flight existingFlight = flightOptional.get();
        // Update the properties of the existingFlight with values from updatedFlight
        existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
        existingFlight.setRoute(updatedFlight.getRoute());
        existingFlight.setPrice(updatedFlight.getPrice());
        existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
        existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
        existingFlight.setCapacity(updatedFlight.getCapacity());
        existingFlight.setStatus(updatedFlight.getStatus());

        return flightRepository.save(existingFlight);
    }

    @Override
    public void deleteFlight(Long flightId) {
        boolean exists = flightRepository.existsById(flightId);
        if (!exists) {
            throw new IllegalStateException("Flight with ID " + flightId + " does not exist");
        }
        flightRepository.deleteById(flightId);
    }

    @Scheduled(fixedRate = 60000) // Update every minute
    public void updateFlightStatus() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            if (currentDateTime.isAfter(flight.getDepartureTime()) && currentDateTime.isBefore(flight.getArrivalTime())) {
                flight.setStatus(FlightStatus.DEPARTED);
            } else if (currentDateTime.isAfter(flight.getArrivalTime())) {
                flight.setStatus(FlightStatus.ARRIVED);
            }
        }

        flightRepository.saveAll(flights);
    }
}
