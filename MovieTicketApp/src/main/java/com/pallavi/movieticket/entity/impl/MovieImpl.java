package com.pallavi.movieticket.entity.impl;

import com.pallavi.movieticket.entity.Movie;

/**
 * This class implements movie interface. All the methods are defined in this
 * class
 * 
 * @author pallavidas
 *
 */
public class MovieImpl implements Movie {

	String name;
	String language;
	String genre;

	/**
	 * Constructor initialization.
	 * 
	 * @param name
	 *            - movie name
	 * @param language
	 *            - movie language (English, hindi etc)
	 * @param genre
	 *            - movie genre(Drama, Thriller etc)
	 */
	public MovieImpl(String name, String language, String genre) {
		this.name = name;
		this.language = language;
		this.genre = genre;
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public String getLanguage() {

		return language;
	}

	@Override
	public String getGenre() {

		return genre;
	}

	@Override
	public String toString() {
		return "name=" + name + ", language=" + language + ", genre=" + genre + "";
	}

}
