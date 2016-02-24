package com.pallavi.movieticket.service;

import java.util.List;

import com.pallavi.movieticket.entity.Movie;

public interface MovieService {
	
	List<Movie> getAllMovies();
	Movie getMovieByName(String name);
	
	
}
