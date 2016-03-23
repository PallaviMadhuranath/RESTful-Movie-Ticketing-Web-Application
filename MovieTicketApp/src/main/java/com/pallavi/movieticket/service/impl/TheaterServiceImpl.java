package com.pallavi.movieticket.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.repository.MovieRepository;
import com.pallavi.movieticket.repository.TheaterRepository;
import com.pallavi.movieticket.service.TheaterService;

/**
 * This class implements Theater service interface. All the methods are defined
 * here.
 * 
 * @author pallavidas
 *
 */
@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterRepository theaterRepo;
	
	@Autowired
	private MovieRepository movieRepo;

	public List<Theater> getAllTheaters() {

		return theaterRepo.getAllTheaters();
	}

	public Theater getTheaterByName(String name) {
		
		return theaterRepo.getTheaterByName(name);
	}
	
	@Override
	public List<Theater> getTheaterByMovie(String movieName) {
		
		return movieRepo.getMovieByName(movieName).getTheaters();
	}

	@Override
	public Theater getTheaterByID(String id) {
		return theaterRepo.getTheaterByID(id) ;
	}

	@Override
	public List<Theater> getTheatersByName(String name) {
		
		return theaterRepo.getTheatersByName(name);
	}
}
