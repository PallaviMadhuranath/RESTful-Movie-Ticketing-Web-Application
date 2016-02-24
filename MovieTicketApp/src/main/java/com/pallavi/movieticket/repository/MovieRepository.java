package com.pallavi.movieticket.repository;

import java.util.List;

import com.pallavi.movieticket.entity.Movie;

public interface MovieRepository {
	
	Movie getMovieByName(String name);
	List<Movie> getAllMovies();
	
}
