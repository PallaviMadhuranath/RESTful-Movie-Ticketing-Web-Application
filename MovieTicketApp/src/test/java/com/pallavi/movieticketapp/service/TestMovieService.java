package com.pallavi.movieticketapp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.service.impl.MovieServiceImpl;

/**
 * This class has methods used for unit testing of movie service.
 * 
 * @author pallavidas
 *
 */
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestMovieService extends AbstractJUnit4SpringContextTests {

	public List<Movie> movieList = new ArrayList<Movie>();
	@Autowired
	private MovieServiceImpl ms;

	/**
	 * Tests positive case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesPositive() {
		System.out.println(ms.getAllMovies());
		Assert.assertEquals(3, ms.getAllMovies().size());

	}

	/**
	 * Tests negative case of number of movies present in the list.
	 */
	@Test
	public void testGetMoviesNegative() {
		Assert.assertNotEquals(4, ms.getAllMovies().size());
	}

	/**
	 * Test positive case to get movie by search.
	 */
	@Test
	public void testGetMovieByNamePositive() {
		Movie movie = ms.getMovieByName("Titanic");
		System.out.println(movie);
		Assert.assertEquals("Titanic", movie.getName());
		Assert.assertEquals("English", movie.getLanguage());
		Assert.assertEquals("Romance", movie.getGenre());
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
