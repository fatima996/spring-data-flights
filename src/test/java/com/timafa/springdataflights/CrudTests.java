package com.timafa.springdataflights;

import com.timafa.springdataflights.entity.Flight;
import com.timafa.springdataflights.repositoy.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CrudTests {

	@Autowired
	private FlightRepository flightRepository;

    @Test
	public void shouldPerformCrudOperations() {
		final Flight flight = new Flight();
		flight.setOrigin("Amsterdam");
		flight.setDestination("New York");
		flight.setScheduleAt(LocalDateTime.parse("2022-12-13T12:12:00"));

	    flightRepository.save(flight);


		assertThat(flightRepository.findAll())
				.hasSize(1)
				.first()
				.isEqualToComparingFieldByField(flight);

		flightRepository.deleteById(flight.getId());

		assertThat(flightRepository.count()).isZero();

	}

}
