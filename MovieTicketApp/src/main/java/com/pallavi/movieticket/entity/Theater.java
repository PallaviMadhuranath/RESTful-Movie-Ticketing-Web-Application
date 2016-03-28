package com.pallavi.movieticket.entity;

import java.util.List;

/**
 * This interface has methods related to theater, like name, address and
 * availability.
 * 
 * @author pallavidas
 *
 */
public interface Theater {
	/**
	 * This method returns the name of the theater.
	 * 
	 * @return - String
	 */
	String getName();

	/**
	 * This method returns the address of the theater
	 * 
	 * @return - String
	 */
	String getCity();

	long getID();
	
	String getZipCode();
	
	public List<Movie> getMovies();

}
