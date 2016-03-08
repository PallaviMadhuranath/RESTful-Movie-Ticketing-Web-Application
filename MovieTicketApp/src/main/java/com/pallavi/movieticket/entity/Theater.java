package com.pallavi.movieticket.entity;

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
	String getAddress();

	int getID();

}
