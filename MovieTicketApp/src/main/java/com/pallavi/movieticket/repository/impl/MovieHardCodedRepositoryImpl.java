package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.MovieImpl;
import com.pallavi.movieticket.repository.MovieRepository;

@Component
public class MovieHardCodedRepositoryImpl implements MovieRepository {

	Movie movieObj;
	private List<Movie> movieList;

	public MovieHardCodedRepositoryImpl() {

		movieList = new ArrayList<Movie>();

		movieList.add(new MovieImpl("Titanic", "English", "Romance"));
		movieList.add(new MovieImpl("Piku", "Hindi", "Comedy"));
		movieList.add(new MovieImpl("Rangitaranga", "Kannada", "Thriller"));
		
	}

	public Movie getMovieByName(String name) {
		for (Movie movie : movieList) {
			if (movie.getName().equals(name)) {
				return movie;
			}
			
		}
		return null;
	}

	public List<Movie> getAllMovies() {
		return movieList;
	}
	
}
