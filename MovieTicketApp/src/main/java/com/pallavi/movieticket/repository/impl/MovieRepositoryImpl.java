package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
	public List<Movie> getMoviesByName(String name) {

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
		if (!StringUtils.isEmpty(name)) {
			crit.add(Restrictions.eq("name", name));
		}

		List<Movie> moviesList = crit.list();

		Set<Movie> moviesSet = new HashSet<Movie>();
		for (Movie movie : moviesList) {
			moviesSet.add(movie);
		}
		return new ArrayList<>(moviesSet);
	}

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

	public Movie getMovieById(long id) {
		return (Movie) this.sessionFactory.getCurrentSession().get(MovieImpl.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAllMovies() {

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
		List<Movie> movies = crit.list();

		return movies;
	}

	@Override
	public long addMovie(Movie movie) {
		return (Long) this.sessionFactory.getCurrentSession().save(movie);
	}

	@Override
	public void deleteMovie(long id) {
		Movie movie = getMovieById(id);
		if(movie != null) {
			this.sessionFactory.getCurrentSession().delete(movie);
		}
	}

}
