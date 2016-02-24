package com.pallavi.movieticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.repository.TheaterRepository;
import com.pallavi.movieticket.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService {
	
	@Autowired
	private TheaterRepository theaterRepo;
	
	public List<Theater> getAllTheaters() {

		return theaterRepo.getAllTheaters() ;
	}

	public Theater getTheaterByName(String name) {
		
		return theaterRepo.getTheaterByName(name);
	}
}
