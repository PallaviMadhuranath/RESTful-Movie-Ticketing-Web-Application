package com.pallavi.movieticket.entity;

/**
 * Movie interface has methods related to movie, like name of the movie,
 * language and Genre,
 * 
 * @author pallavidas
 *
 */
public interface Movie {

	/**
	 * This method returns movie name.
	 * 
	 * @return - String
	 */
	String getName();

	/**
	 * This method returns movie language like, English, hindi, kannada.
	 * 
	 * @return - String
	 */
	String getLanguage();

	/**
	 * This method returns movie genre like, Drama, Thriller etc.
	 * 
	 * @return - String
	 */
	String getGenre();
}
