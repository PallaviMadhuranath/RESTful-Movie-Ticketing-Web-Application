package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.MovieImpl;
import com.pallavi.movieticket.repository.MovieRepository;

/**
 * This class implements MovieRepository interface. All the methods are defined
 * here. it contains hard coded values of movies.
 * 
 * @author pallavidas
 *
 */
@Component
public class MovieHardCodedRepositoryImpl implements MovieRepository {

	Movie movieObj;
	private List<Movie> movieList;

	/**
	 * Constructor initialization. Initializes movie list with hard coded
	 * values.
	 */
	public MovieHardCodedRepositoryImpl() {

		movieList = new ArrayList<Movie>();

		movieList.add(new MovieImpl("Titanic", "English", "Romance"));
		movieList.add(new MovieImpl("Piku", "Hindi", "Comedy"));
		movieList.add(new MovieImpl("Rangitaranga", "Kannada", "Thriller"));

	}

	@Override
	public Movie getMovieByName(String name) {
		for (Movie movie : movieList) {
			if (movie.getName().equalsIgnoreCase(name)) {
				return movie;
			}

		}
		return null;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieList;
	}

}
