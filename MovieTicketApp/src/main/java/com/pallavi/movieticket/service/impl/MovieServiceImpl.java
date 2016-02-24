package com.pallavi.movieticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.repository.MovieRepository;
import com.pallavi.movieticket.service.MovieService;

/**
 * This class implements Movie service interface. All the methods are defined
 * here.
 * 
 * @author pallavidas
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepo;

	public List<Movie> getAllMovies() {
		return movieRepo.getAllMovies();

	}

	public Movie getMovieByName(String name) {

		return movieRepo.getMovieByName(name);
	}

}
