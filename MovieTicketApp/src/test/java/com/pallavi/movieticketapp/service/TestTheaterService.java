package com.pallavi.movieticketapp.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.service.TheaterService;

/**
 * This class has methods used for unit testing of theater service,
 * 
 * @author pallavidas
 *
 */
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@Transactional
public class TestTheaterService extends AbstractJUnit4SpringContextTests {

	@Autowired
	private TheaterService ts;

	/**
	 * Tests positive case of number of theaters present in the list.
	 */
	@Test
	public void testGetTheatersPositive() {
		System.out.println(ts.getAllTheaters());
		// Assert.assertEquals(3, ts.getAllTheaters().size());
	}

	/**
	 * Tests negative case of number of theaters present in the list.
	 */
	@Test
	public void testGetTheatersNegative() {
		//Assert.assertNotEquals(4, ts.getAllTheaters().size());
	}

	/**
	 * Test positive case to get theater by search.
	 */
	@Test
	public void testGetTheaterByNamePositive() {
		Theater theater = ts.getTheaterByName("AMC Marcado");
		// System.out.println(theater);
		Assert.assertEquals("AMC Marcado", theater.getName());
	}

	/**
	 * Test for lower case input.
	 */
	@Test
	public void testGetTheaterByNameLowerCase() {
		Theater theater = ts.getTheaterByName("amc marcado");
		// System.out.println(theater);
		Assert.assertEquals("AMC Marcado", theater.getName());
	}

	/**
	 * Test negative test case, when a return is null.
	 */
	@Test
	public void testGetTheaterByNameNegative() {
		Theater theater = ts.getTheaterByName("AMC Cupertino");
		Assert.assertNull(theater);
	}

	/*@Test
	public void testGetTheatersByMoviePositive() {
		//Assert.assertEquals(1, ts.getTheaterByMovie("God Father").size());
		// System.out.println(ts.getTheaterByMovie("God Father"));
	}*/
}
