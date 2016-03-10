package com.pallavi.movieticket.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Theater;

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

	@ManyToMany(targetEntity = TheaterImpl.class, fetch = FetchType.EAGER)
	@JoinTable(name = "movies_theatres", joinColumns = {
			@JoinColumn(name = "movies_movie_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "theatres_theatre_id", nullable = false) })
	private List<Theater> theaters;

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

	public void setName(String movieName) {
		this.name = movieName;
	}

	@Override
	public String getLanguage() {

		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String getGenre() {

		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "name=" + name + ", language=" + language + ", genre=" + genre + "";
	}

	@Override
	public int getID() {
		return id;
	}

	public List<Theater> getTheaters() {
		return theaters;
	}

	public void addgetTheaters(Theater theater) {
		if (theaters == null) {
			theaters = new ArrayList<Theater>();
		}
		theaters.add(theater);
	}

}
