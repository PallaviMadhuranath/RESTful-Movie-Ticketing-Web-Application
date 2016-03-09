package com.pallavi.movieticket.repository;

import java.util.List;

import com.pallavi.movieticket.entity.Movie;

/**
 * This interface acts like a data access object interface. It has methods for
 * the movie service to access movie data.
 * 
 * @author pallavidas
 *
 */
public interface MovieRepository {

	/**
	 * This method returns the choice of movie name from the current list.
	 * 
	 * @param name
	 *            - movie name
	 * @return - Movie
	 */
	Movie getMovieByName(String name);

	/**
	 * This method returns list of movies.
	 * 
	 * @return - List
	 */
	List<Movie> getAllMovies();
	
	
	
	
	

}
