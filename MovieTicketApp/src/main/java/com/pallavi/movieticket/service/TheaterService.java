package com.pallavi.movieticket.service;

import java.util.List;

import com.pallavi.movieticket.entity.Theater;

/**
 * This is a Theater service interface. It has the theater service features like
 * fetching theater by name and fetching list of all theaters.
 * 
 * @author pallavidas
 *
 */
public interface TheaterService {

	/**
	 * This is a theater service method returns list of all theaters
	 * 
	 * @return - List
	 */
	List<Theater> getAllTheaters();

	/**
	 * This is a theater service method returns choice of theater.
	 * 
	 * @param name
	 *            - theater name
	 * @return - Theater
	 */
	Theater getTheaterByName(String name);

	List<Theater> getTheaterByMovie(String movieName);

}
