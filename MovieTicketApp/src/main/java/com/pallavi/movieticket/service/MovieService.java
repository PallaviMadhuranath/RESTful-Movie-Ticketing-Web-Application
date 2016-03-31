package com.pallavi.movieticket.service;

import java.util.List;

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
	List<Movie> getMoviesByName(String name);
	
	Movie getMovieByName(String name);

	Movie getMovieById(long id);
	
	List<Movie> getMovieByTheater(String name);
	
	Movie addMovie(Movie movie);
	
	void deleteMovie(long id);
	
	//List<Movie> getMovieByUser(long id);

}
