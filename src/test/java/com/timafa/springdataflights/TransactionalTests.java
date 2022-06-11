package com.timafa.springdataflights;

import com.timafa.springdataflights.entity.Flight;
import com.timafa.springdataflights.repositoy.FlightRepository;
import com.timafa.springdataflights.servis.FlightService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTests {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void shouldNotRollBackWhenThereIsNoTransaction() {

        try {
            flightService.saveFlight(new Flight());
        } catch (Exception e) {

        } finally {
            Assertions.assertThat(flightRepository.findAll()).isNotEmpty();
        }
    }

    @Test
    public void shouldRollBackWhenThereIsNoTransaction() {

        try {
            flightService.saveFlightTransactional(new Flight());
        } catch (Exception e) {

        } finally {
            Assertions.assertThat(flightRepository.findAll()).isEmpty();
        }
    }
}
