package com.pallavi.movieticketapp.service;

import org.junit.Test;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.service.MovieService;

/**
 * This class has methods used for unit testing of movie service.
 * 
 * @author pallavidas
 *
 */

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@Transactional
public class TestMovieService extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MovieService ms;

	/**
	 * Tests positive case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesPositive() {
		// System.out.println(ms.getAllMovies());
		Assert.assertEquals(2, ms.getAllMovies().size());

	}

	/**
	 * Tests negative case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesNegative() {
		Assert.assertNotEquals(3, ms.getAllMovies().size());
	}

	/**
	 * Test positive case to get movie by search.
	 */

	@Test
	public void testGetMovieByNamePositive() {
		Movie movie = ms.getMovieByName("God Father");
		Assert.assertEquals("God Father", movie.getName());
	}

	/**
	 * Test for lower case input.
	 **/

	@Test
	public void testGetMovieByNameLowerCase() {
		Movie movie = ms.getMovieByName("god father");
		// System.out.println(movie);
		Assert.assertEquals("God Father", movie.getName());
	}

	/**
	 * Test negative test case, when a return is null.
	 */
	@Test
	public void testGetMovieByNameNegative() {
		Movie movie = ms.getMovieByName("Deadpool");
		Assert.assertNull(movie);
	}

	@Test
	public void testMovieByTheaterPositive() {
		Assert.assertEquals(2, ms.getMovieByTheater("AMC Marcado").size());
		System.out.println(ms.getMovieByTheater("AMC Marcado"));
	}

	@Test
	public void testMovieByTheaterNegative() {
		Assert.assertNotEquals(3, ms.getMovieByTheater("AMC Marcado").size());
	}

}
