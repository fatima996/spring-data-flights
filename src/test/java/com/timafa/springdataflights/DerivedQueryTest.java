package com.timafa.springdataflights;

import com.timafa.springdataflights.entity.Flight;
import com.timafa.springdataflights.repositoy.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DerivedQueryTest {

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    public void  setUp(){
        flightRepository.deleteAll();
    }

    @Test
    public void shouldFindFlightsFromLondon(){
        final Flight flight1 = createFlight("London");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("New York");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsToLondon = flightRepository.findByOrigin("London");

        assertThat(flightsToLondon).hasSize(2);
        assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByFieldRecursively(flight1);
        assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByFieldRecursively(flight2);

    }
    @Test
    public void shouldFindFlightsFromLondonToParis(){
        final Flight flight1 = createFlight("London", "Paris");
        final Flight flight2 = createFlight("London", " New York");
        final Flight flight3 = createFlight("Madrid", "Paris");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsToLondon = flightRepository.findByOriginAndDestination("London", "Paris");

        assertThat(flightsToLondon).hasSize(1).first().isEqualToComparingFieldByField(flight1);

    }

    @Test
    public void shouldFindFlightsFromLondonOrMadrid(){
        final Flight flight1 = createFlight("London", "Paris");
        final Flight flight2 = createFlight("Tokyo", " New York");
        final Flight flight3 = createFlight("Madrid", "Paris");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsToLondon = flightRepository.findByOriginIn("London", "Madrid");

        assertThat(flightsToLondon).hasSize(2).first().isEqualToComparingFieldByField(flight1);
    }
    @Test
    public void shouldFindFlightsFromLondonIgnoringCase(){
        final Flight flight1 = createFlight("London", "Paris");
        final Flight flight2 = createFlight("Tokyo", " New York");
        final Flight flight3 = createFlight("Madrid", "Paris");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> flightsToLondon = flightRepository.findByOriginIgnoreCase("London");

        assertThat(flightsToLondon).hasSize(1).first().isEqualToComparingFieldByField(flight1);
    }
    private Flight createFlight(String origin){
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination("Madrid");
        flight.setScheduleAt(LocalDateTime.parse("2022-12-13T12:12:00"));
        return flight;
    }

    private Flight createFlight(String origin, String destination){
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setScheduleAt(LocalDateTime.parse("2022-12-13T12:12:00"));
        return flight;
    }
}
