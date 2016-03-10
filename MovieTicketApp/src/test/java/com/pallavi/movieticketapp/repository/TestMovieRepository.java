package com.pallavi.movieticketapp.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

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

		Assert.assertEquals(2, movieRepo.getAllMovies().size());

	}

	public void getMovieByName() {
		Assert.assertEquals("Titanic", movieRepo.getMovieByName("Titanic"));

	}
}
