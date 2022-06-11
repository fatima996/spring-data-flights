package com.timafa.springdataflights.servis;

import com.timafa.springdataflights.entity.Flight;
import com.timafa.springdataflights.repositoy.FlightRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void saveFlight(Flight flight){
        flightRepository.save(flight);
        throw new RuntimeException("I failed");

    }
    @Transactional
    public void saveFlightTransactional(Flight flight){
        flightRepository.save(flight);
        throw new RuntimeException("I failed");

    }
}
