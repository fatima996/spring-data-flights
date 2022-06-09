package com.timafa.springdataflights;

import com.timafa.springdataflights.entity.Flight;
import com.timafa.springdataflights.repositoy.FlightRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PagingAndSortingTest {

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void shouldSortFlightsByDestination() {
        final Flight flight1 = createFlight("Madrid");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("New York");

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        final Iterable<Flight> flights = flightRepository.findAll(Sort.by("destination"));

        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }

        assertThat(flights).hasSize(3);

        final Iterator<Flight> iterator = flights.iterator();

        assertThat(iterator.next().getDestination()).isEqualTo("London");
        assertThat(iterator.next().getDestination()).isEqualTo("Madrid");
        assertThat(iterator.next().getDestination()).isEqualTo("New York");
    }

    private Flight createFlight(String origin) {
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination("Madrid");
        flight.setScheduleAt(LocalDateTime.parse("2022-12-13T12:12:00"));
        return flight;
    }


}
