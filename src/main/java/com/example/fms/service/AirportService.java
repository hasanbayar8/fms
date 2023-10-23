package com.example.fms.service;

import com.example.fms.entity.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> getAirports();
    Airport createAirport(Airport airport);

    Airport updateAirport(Long airportId, Airport updatedAirport);

    void deleteAirport(Long airportId);
}
