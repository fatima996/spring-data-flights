package com.timafa.springdataflights.repositoy;

import com.timafa.springdataflights.entity.Flight;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {
    List<Flight> findByOrigin(String origin);

    List<Flight> findByOriginAndDestination(String origin, String destination);

    List<Flight> findByOriginIn(String... origin);

    List<Flight> findByOriginIgnoreCase(String origin);
}
