package com.pallavi.movieticket.service;

import java.util.List;

import com.pallavi.movieticket.entity.Theater;

public interface TheaterService {
	
	List<Theater> getAllTheaters();
	Theater getTheaterByName(String name);
	
	
}
