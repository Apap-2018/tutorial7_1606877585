package com.apap.tutorial07.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial07.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);

    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
    Optional<FlightModel> getFLightDetailByFlightId(long id);
    Optional<List<FlightModel>> getAll();
}