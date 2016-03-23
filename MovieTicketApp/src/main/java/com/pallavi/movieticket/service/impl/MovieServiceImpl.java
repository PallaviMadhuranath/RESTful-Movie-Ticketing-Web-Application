package com.pallavi.movieticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.repository.MovieRepository;
import com.pallavi.movieticket.repository.TheaterRepository;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.MovieTicketException;

/**
 * This class implements Movie service interface. All the methods are defined
 * here.
 * 
 * @author pallavidas
 *
 */

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private TheaterRepository theaterRepo;

	public List<Movie> getAllMovies() {
		return movieRepo.getAllMovies();

	}

	public List<Movie> getMoviesByName(String name) {
		List<Movie> returnList = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			throw new MovieTicketException(ErrorCode.MISSING_DATA,"no search parameter provided");
		} 
		else {

			returnList = movieRepo.getMoviesByName(name);
		}

		return returnList;
	}

	public Movie getMovieByName(String name) {
		return movieRepo.getMovieByName(name);
	}

	@Override
	public List<Movie> getMovieByTheater(String theaterName) {

		return theaterRepo.getTheaterByName(theaterName).getMovies();
	}

	public Movie getMovieById(String id) {
		return movieRepo.getMovieById(id);
	}

}
