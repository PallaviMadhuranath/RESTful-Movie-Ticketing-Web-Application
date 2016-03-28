package com.pallavi.movieticketapp.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
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
		System.out.println(theaterRepo.getAllTheaters().size());
		//Assert.assertEquals(3, theaterRepo.getAllTheaters().size());
	}

	public void getThetaerByName() {
		Assert.assertEquals("Century", theaterRepo.getTheaterByName("Century"));
	}

	@Test
	/**
	 * Test for adding new movie to movie repository
	 */
	public void addAndGetTheaters() {
		TheaterImpl newTheater = new TheaterImpl();
		newTheater.setName("AMC Cupertino");
		newTheater.setCity("Cupertino");
		newTheater.setZipCode("95014");

		long addedTheaterId = theaterRepo.addTheater(newTheater);
		System.out.println("theater added id " + addedTheaterId);
		Assert.assertNotEquals(0, addedTheaterId);

		Theater theater = theaterRepo.getTheaterByID(addedTheaterId);
		Assert.assertEquals(theater.getID(), addedTheaterId);
		Assert.assertEquals(theater.getName(), newTheater.getName());
		Assert.assertEquals(theater.getCity(), newTheater.getCity());
		Assert.assertEquals(theater.getZipCode(), newTheater.getZipCode());

	}
}
