package com.example.fms.service;

import com.example.fms.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);
    Flight getFlightById(Long flightId);
    Flight updateFlight(Long flightId, Flight updatedFlight);
    void deleteFlight(Long flightId);
    List<Flight> getAllFlights();
}

