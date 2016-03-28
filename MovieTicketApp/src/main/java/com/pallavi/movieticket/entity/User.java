package com.pallavi.movieticket.entity;

import java.util.List;

public interface User {

	/**
	 * Unique user id
	 * 
	 * @return
	 */
	long getId();

	/**
	 * name of the user
	 * 
	 * @return - type String
	 */
	String getFirstName();
	
	String getLastName();

	/**
	 * user email address
	 * 
	 * @return - type String
	 */
	String getEmailAddress();
	
	String getPassword();
	
	//List<Movie> getMovies();
	//void addUserMovie(Movie movie);

}
