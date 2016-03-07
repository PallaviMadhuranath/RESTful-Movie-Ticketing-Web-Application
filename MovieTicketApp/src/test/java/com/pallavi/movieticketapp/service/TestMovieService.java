package com.pallavi.movieticketapp.service;

import org.junit.Test;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.impl.MovieServiceImpl;

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
		System.out.println(ms.getAllMovies());
		Assert.assertEquals(1, ms.getAllMovies().size());

	}

	/**
	 * Tests negative case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesNegative() {
		Assert.assertNotEquals(2, ms.getAllMovies().size());
	}

	/**
	 * Test positive case to get movie by search.
	 */

	@Test
	public void testGetMovieByNamePositive() {
		Movie movie = ms.getMovieByName("God Father");
		System.out.println(movie);
		Assert.assertEquals("God Father", movie.getName());
		// Assert.assertEquals("English", movie.getLanguage());
		// Assert.assertEquals("Romance", movie.getGenre());
	}

	/**
	 * Test for lower case input.
	 **/

	@Test
	public void testGetMovieByNameLowerCase() {
		Movie movie = ms.getMovieByName("god father");
		System.out.println(movie);
		Assert.assertEquals("God Father", movie.getName());
	}

	/**
	 * Test negative test case, when a return is null.
	 */
	@Test
	public void testGetMovieByNameNegative() {
		Movie movie = ms.getMovieByName("Deadpool");
		System.out.println(movie);
		Assert.assertNull(movie);
	}

}
