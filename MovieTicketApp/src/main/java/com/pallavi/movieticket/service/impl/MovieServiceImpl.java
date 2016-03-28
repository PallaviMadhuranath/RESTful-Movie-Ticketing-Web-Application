package com.pallavi.movieticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.impl.UserImpl;
import com.pallavi.movieticket.repository.MovieRepository;
import com.pallavi.movieticket.repository.TheaterRepository;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.InvalidFieldException;
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
	
	private static final int MAX_NAME_LENGTH = 45;

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

	public Movie getMovieById(long id) {
		return movieRepo.getMovieById(id);
	}

	@Override
	public Movie addMovie(Movie movie) {
		if(StringUtils.isEmpty(movie.getName()) || movie.getName().length() > MAX_NAME_LENGTH){			
			throw new InvalidFieldException("Movie name is required");
		}
		
		if(StringUtils.isEmpty(movie.getLanguage()) || movie.getLanguage().length()>MAX_NAME_LENGTH){			
			throw new InvalidFieldException("Language required is required");
		}
		
		if(StringUtils.isEmpty(movie.getGenre()) || movie.getGenre().length()>MAX_NAME_LENGTH){			
			throw new InvalidFieldException("Genre is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5	
		long id =  movieRepo.addMovie(movie);
		return getMovieById(id);
	}

}
