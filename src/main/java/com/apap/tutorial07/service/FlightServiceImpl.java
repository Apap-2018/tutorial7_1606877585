package com.apap.tutorial07.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial07.model.FlightModel;
import com.apap.tutorial07.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFLightDetailByFlightId(long id) {
        return flightDb.findById(id);
    }

    @Override
    public Optional<List<FlightModel>> getAll() {
        return Optional.of(flightDb.findAll());
    }
}