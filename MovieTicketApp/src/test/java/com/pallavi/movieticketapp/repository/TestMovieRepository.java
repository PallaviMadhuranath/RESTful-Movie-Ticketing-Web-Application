package com.pallavi.movieticketapp.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.MovieImpl;
import com.pallavi.movieticket.repository.MovieRepository;

/**
 * This class is to unit test MovieRepository
 * 
 * @author pallavidas
 *
 */

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestMovieRepository extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MovieRepository movieRepo;

	/**
	 * Tests for getAllMovies
	 */
	@Test
	public void getMovies() {
		System.out.println("From movie Repo");
		System.out.println(movieRepo.getAllMovies().size());
		List<Movie> movies = (movieRepo.getAllMovies());
		for(Movie movie : movies){
			System.out.println(movie);
		}
		

		//Assert.assertEquals(4, movieRepo.getAllMovies().size());

	}

	/**
	 * Test for retrieving movie by name
	 */
	public void getMovieByName() {
		Assert.assertEquals("Titanic", movieRepo.getMovieByName("Titanic"));
	}

	@Test
	/**
	 * Test for adding new movie to movie repository
	 */
	public void addAndGetMovies() {
		MovieImpl newMovie = new MovieImpl();
		newMovie.setName("Zootapia");
		newMovie.setLanguage("English");
		newMovie.setGenre("Animation");

		long addedMovieId = movieRepo.addMovie(newMovie);
		System.out.println("movie added id " + addedMovieId);
		Assert.assertNotEquals(0, addedMovieId);

		Movie movie = movieRepo.getMovieById(addedMovieId);
		Assert.assertEquals(movie.getID(), addedMovieId);
		Assert.assertEquals(movie.getName(), newMovie.getName());
		Assert.assertEquals(movie.getLanguage(), newMovie.getLanguage());
		Assert.assertEquals(movie.getGenre(), newMovie.getGenre());

	}
}
