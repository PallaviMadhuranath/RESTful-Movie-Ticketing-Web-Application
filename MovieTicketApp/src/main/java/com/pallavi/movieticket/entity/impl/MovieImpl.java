package com.pallavi.movieticket.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;

/**
 * This class implements movie interface. All the methods are defined in this
 * class
 * 
 * @author pallavidas
 *
 */

@Entity
@Table(name = "movies")
public class MovieImpl implements Movie {

	@Id
	@Column(name = "Movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Title")
	String name;

	@Column(name = "Language")
	String language;

	@Column(name = "Genre")
	String genre;

	public MovieImpl() {

	}

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

	@Override
	public int getID() {
		return id;
	}

}
