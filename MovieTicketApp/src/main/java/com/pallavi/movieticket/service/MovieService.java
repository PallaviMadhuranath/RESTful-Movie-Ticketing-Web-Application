package com.pallavi.movieticket.service;

import java.util.List;

import javax.transaction.Transactional;

import com.pallavi.movieticket.entity.Movie;

/**
 * This is a Movie service interface. It has the movie service features like
 * fetching movie by name and fetching list of all movies.
 * 
 * @author pallavidas
 *
 */
public interface MovieService {

	/**
	 * This is a movie service method returns list of all movies
	 * 
	 * @return - List
	 */
	List<Movie> getAllMovies();

	/**
	 * This is a movie service method returns choice of movie.
	 * 
	 * @param name
	 *            - movie name
	 * @return - Movie
	 */
	Movie getMovieByName(String name);
	
	List<Movie> getMovieByTheater(String name);

}
