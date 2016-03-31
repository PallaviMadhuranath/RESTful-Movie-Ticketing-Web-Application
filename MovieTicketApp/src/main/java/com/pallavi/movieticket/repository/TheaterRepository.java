package com.pallavi.movieticket.repository;

import java.util.List;

import com.pallavi.movieticket.entity.Theater;

/**
 * This interface acts like a data access object interface. It has methods for
 * the Theater service to access theater data.
 * 
 * @author pallavidas
 *
 */
public interface TheaterRepository {

	/**
	 * This method returns list of theaters.
	 * 
	 * @return - List
	 */
	List<Theater> getAllTheaters();

	/**
	 * This method returns choice of theater
	 * 
	 * @param name
	 *            - theater name
	 * @return - Theater
	 */
	Theater getTheaterByName(String name);
	
	List<Theater> getTheatersByName(String name);
	
	Theater getTheaterByID(long id);
	
	long addTheater(Theater theater);
	
	void deleteTheater(long id);
}
