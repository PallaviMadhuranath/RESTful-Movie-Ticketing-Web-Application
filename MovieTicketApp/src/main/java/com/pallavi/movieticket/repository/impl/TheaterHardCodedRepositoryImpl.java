package com.pallavi.movieticket.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pallavi.movieticket.entity.Theater;
import com.pallavi.movieticket.entity.impl.TheaterImpl;
import com.pallavi.movieticket.repository.TheaterRepository;

/**
 * This class implements TheaterRepository interface. All the methods are
 * defined here. it contains hard coded values of theater.
 * 
 * @author pallavidas
 *
 */
@Component
public class TheaterHardCodedRepositoryImpl implements TheaterRepository {

	private List<Theater> theaterList;

	/**
	 * Constructor initialization. Initializes theater list with hard coded
	 * values.
	 */
	public TheaterHardCodedRepositoryImpl() {
		theaterList = new ArrayList<Theater>();

		theaterList.add(new TheaterImpl("AMC Mercado", "Santa Clara"));
		theaterList.add(new TheaterImpl("Century Cinemas", "Mountain View"));
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
