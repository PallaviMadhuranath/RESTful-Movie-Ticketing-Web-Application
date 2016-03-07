package com.pallavi.movieticket.repository.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Constructor initialization. Initializes movie list with hard coded
	 * values.
	 */
	public MovieRepositoryImpl() {

	}

	// @Override
	@SuppressWarnings("unchecked")
	public Movie getMovieByName(String name) {

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
		List<Movie> movies = crit.list();

		for (Movie movie : movies) {
			if (movie.getName().equalsIgnoreCase(name)) {
				return movie;
			}

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAllMovies() {

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
		List<Movie> movies = crit.list();

		return movies;
	}

}
