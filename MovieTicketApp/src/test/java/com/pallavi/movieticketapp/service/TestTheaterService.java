package com.pallavi.movieticketapp.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.service.impl.TheaterServiceImpl;

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestTheaterService extends AbstractJUnit4SpringContextTests {

	@Autowired
	private TheaterServiceImpl ts;

	@Test
	public void testGetTheatersPositive() {
		System.out.println(ts.getAllTheaters());
		Assert.assertEquals(2, ts.getAllTheaters().size());
	}

	public void testGetTheatersNegative() {
		Assert.assertNotEquals(4, ts.getAllTheaters().size());
	}

	@Test
	public void testGetTheaterByNamePositive() {
		Theater theater = ts.getTheaterByName("AMC Mercado");
		System.out.println(theater);
		Assert.assertEquals("AMC Mercado", theater.getName());
	}

	@Test
	public void testGetTheaterByNameNegative() {
		Theater theater = ts.getTheaterByName("AMC Cupertino");
		Assert.assertNull(theater);
	}
}
