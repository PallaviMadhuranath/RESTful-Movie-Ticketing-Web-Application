package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
import com.pallavi.movieticket.repository.TheaterRepository;

@Component
public class TheaterHardCodedRepositoryImpl implements TheaterRepository {
	
	private List<Theater> theaterList;
	
	public TheaterHardCodedRepositoryImpl(){
		theaterList = new ArrayList<Theater>();
		
		theaterList.add(new TheaterImpl("AMC Mercado", "Santa Clara", 20));
		theaterList.add(new TheaterImpl("Century Cinemas", "Mountain View", 20));
	}

	@Override
	public List<Theater> getAllTheaters() {
		return theaterList;
	}

	@Override
	public Theater getTheaterByName(String name) {
		for (Theater theater : theaterList) {
			if (theater.getName().equals(name)) {
				return theater;
			}
		}
		
		return null;
	}

}
