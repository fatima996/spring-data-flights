package com.timafa.springdataflights;

import com.timafa.springdataflights.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SpringDataFlightsApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void verifyFlightCanBeSaved() {
		final Flight flight = new Flight();
		flight.setOrigin("Amsterdam");
		flight.setDestination("New York");
		flight.setScheduleAt(LocalDateTime.parse("2022-12-13T12:12:00"));

		entityManager.persist(flight);
		final TypedQuery<Flight> result = entityManager.createQuery("SELECT f FROM Flight f", Flight.class);

		final List<Flight> resultList = result.getResultList();

		assertThat(resultList)
				.hasSize(1)
				.first()
				.isEqualTo(flight);

	}

}
