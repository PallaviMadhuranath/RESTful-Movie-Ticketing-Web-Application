package com.pallavi.movieticketapp.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.MovieImpl;
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
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieService movieService;

	/**
	 * Tests positive case of number of movies present in the list.
	 */
	/*
	 * @Test public void testGetMoviesPositive() { //
	 * System.out.println(ms.getAllMovies()); Assert.assertEquals(4,
	 * movieService.getAllMovies().size());
	 * 
	 * 
	 * }
	 */

	/**
	 * Tests negative case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesNegative() {
		Assert.assertNotEquals(2, movieService.getAllMovies().size());
	}

	/**
	 * Test positive case to get movie by search.
	 */

	@Test
	public void testGetMovieByNamePositive() {
		Movie movie = movieService.getMovieByName("God Father");
		Assert.assertEquals("God Father", movie.getName());
	}

	/**
	 * Test for lower case input.
	 **/

	@Test
	public void testGetMovieByNameLowerCase() {
		Movie movie = movieService.getMovieByName("god father");
		// System.out.println(movie);
		Assert.assertEquals("God Father", movie.getName());
	}

	/**
	 * Test negative test case, when a return is null.
	 */
	@Test
	public void testGetMovieByNameNegative() {
		Movie movie = movieService.getMovieByName("Deadpool");
		Assert.assertNull(movie);
	}

	@Test
	public void addAndGetMovie() {
		MovieImpl newMovie = new MovieImpl();
		newMovie.setName("Zootapia");
		newMovie.setLanguage("English");
		newMovie.setGenre("Animation");

		Movie added = movieService.addMovie(newMovie);
		logger.info("movie added " + added);
		Assert.assertNotEquals(0, added.getID());
		Assert.assertEquals(newMovie.getName(), added.getName());
		Assert.assertEquals(newMovie.getLanguage(), added.getLanguage());
		Assert.assertEquals(newMovie.getGenre(), added.getGenre());
	}

	@Test
	public void testMovieByTheaterPositive() {
		//Assert.assertEquals(2, movieService.getMovieByTheater("AMC Marcado").size());
		System.out.println(movieService.getMovieByTheater("AMC Marcado"));
	}

	@Test
	public void testMovieByTheaterNegative() {
		Assert.assertNotEquals(3, movieService.getMovieByTheater("AMC Marcado").size());
	}
	
	/*@Test
	public void testGetMovieByUserId(){
		System.out.println(movieService.getMovieById(1));
	}*/

}
