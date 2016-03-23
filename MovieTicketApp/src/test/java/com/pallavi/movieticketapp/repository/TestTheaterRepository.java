package com.pallavi.movieticketapp.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.pallavi.movieticket.repository.TheaterRepository;

/**
 * This class does unit test for theater repository
 * 
 * @author pallavidas
 *
 */

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestTheaterRepository extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TheaterRepository theaterRepo;

	@Test
	public void getAllTheaters() {
		Assert.assertEquals(2, theaterRepo.getAllTheaters().size());
	}

	public void getThetaerByName() {
		Assert.assertEquals("Century", theaterRepo.getTheaterByName("Century"));
	}
}
