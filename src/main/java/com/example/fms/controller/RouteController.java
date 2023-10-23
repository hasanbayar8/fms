package com.example.fms.controller;

import com.example.fms.entity.Route;
import com.example.fms.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Create a new route
    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    // Read route details
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    // Read route details by ID
    @GetMapping("/{routeId}")
    public Route getRouteById(@PathVariable Long routeId) {
        return routeService.getRouteById(routeId);
    }

    // Update route details
    @PutMapping("/{routeId}")
    public Route updateRoute(@PathVariable Long routeId, @RequestBody Route route) {
        return routeService.updateRoute(routeId, route);
    }

    // Delete a route
    @DeleteMapping("/{routeId}")
    public void deleteRoute(@PathVariable Long routeId) {
        routeService.deleteRoute(routeId);
    }

}
