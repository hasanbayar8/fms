package com.example.fms.controller;

import com.example.fms.entity.Airport;
import com.example.fms.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Create a new airport
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    // Read airport details
    @GetMapping
    public List<Airport> getAirports() {
        return airportService.getAirports();
    }

    // Update airport details
    @PutMapping("/{airportId}")
    public Airport updateAirport(@PathVariable Long airportId, @RequestBody Airport airport) {
        return airportService.updateAirport(airportId, airport);
    }

    // Delete an airport
    @DeleteMapping("/{airportId}")
    public void deleteAirport(@PathVariable Long airportId) {
        airportService.deleteAirport(airportId);
    }

    // List all airports
    @GetMapping("/all")
    public List<Airport> getAllAirports() {
        return airportService.getAirports();
    }
}



