package com.example.fms.service;

import com.example.fms.entity.Route;

import java.util.List;

public interface RouteService {
    Route createRoute(Route route);
    Route getRouteById(Long routeId);
    Route updateRoute(Long routeId, Route updatedRoute);
    void deleteRoute(Long routeId);
    List<Route> getAllRoutes();
}
