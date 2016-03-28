package com.pallavi.movieticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.repository.MovieRepository;
import com.pallavi.movieticket.repository.TheaterRepository;
import com.pallavi.movieticket.service.TheaterService;
import com.pallavi.movieticket.service.exception.ErrorCode;
import com.pallavi.movieticket.service.exception.InvalidFieldException;
import com.pallavi.movieticket.service.exception.MovieTicketException;

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
	
	private static final int MAX_NAME_LENGTH = 45;
	private static final int MAX_ZIPCODE_LENGTH = 6;

	@Autowired
	private TheaterRepository theaterRepo;
	
	@Autowired
	private MovieRepository movieRepo;

	public List<Theater> getAllTheaters() {

		return theaterRepo.getAllTheaters();
	}

	public List<Theater> getTheatersByName(String name) {
		List<Theater> returnList = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			throw new MovieTicketException(ErrorCode.MISSING_DATA,"no search parameter provided");
		} 
		else {

			returnList = theaterRepo.getTheatersByName(name);
		}

		return returnList;		
	}
	
	@Override
	public List<Theater> getTheaterByMovie(String movieName) {
		
		return movieRepo.getMovieByName(movieName).getTheaters();
	}

	@Override
	public Theater getTheaterByID(long id) {
		return theaterRepo.getTheaterByID(id) ;
	}

	@Override
	public Theater getTheaterByName(String name) {
		
		return theaterRepo.getTheaterByName(name);
	}

	@Override
	public Theater addTheater(Theater theater) {
		if(StringUtils.isEmpty(theater.getName()) || theater.getName().length() > MAX_NAME_LENGTH){			
			throw new InvalidFieldException("Theater name is required");
		}
		
		if(StringUtils.isEmpty(theater.getCity()) || theater.getCity().length()>MAX_NAME_LENGTH){			
			throw new InvalidFieldException("City name required is required");
		}
		
		if(StringUtils.isEmpty(theater.getZipCode()) || theater.getZipCode().length()>MAX_ZIPCODE_LENGTH){			
			throw new InvalidFieldException("Genre is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5	
		long id =  theaterRepo.addTheater(theater);
		return getTheaterByID(id);
	}
}
