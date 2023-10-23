package com.example.fms.service;

import com.example.fms.entity.Route;
import com.example.fms.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // Create a new route
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    // Read all route details
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Read route details by ID
    public Route getRouteById(Long routeId) {
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (routeOptional.isEmpty()) {
            throw new IllegalStateException("Route with ID " + routeId + " does not exist");
        }
        return routeOptional.get();
    }

    // Update route details
    public Route updateRoute(Long routeId, Route updatedRoute) {
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (routeOptional.isEmpty()) {
            throw new IllegalStateException("Route with ID " + routeId + " does not exist");
        }

        Route existingRoute = routeOptional.get();
        // Update the properties of the existingRoute with values from updatedRoute
        existingRoute.setSource(updatedRoute.getSource());
        existingRoute.setDestination(updatedRoute.getDestination());
        existingRoute.setDistanceInMiles(updatedRoute.getDistanceInMiles());

        return routeRepository.save(existingRoute);
    }

    // Delete a route
    public void deleteRoute(Long routeId) {
        boolean exists = routeRepository.existsById(routeId);
        if (!exists) {
            throw new IllegalStateException("Route with ID " + routeId + " does not exist");
        }
        routeRepository.deleteById(routeId);
    }
}
