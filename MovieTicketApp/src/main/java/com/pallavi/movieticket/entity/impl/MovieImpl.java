package com.pallavi.movieticket.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Showtime;
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
	private long id;

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

	@OneToMany(mappedBy = "movie", targetEntity = ShowtimeImpl.class, cascade = CascadeType.ALL)
	private List<Showtime> showtime;

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
	public MovieImpl(long id, String name, String language, String genre) {
		this.id = id;
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

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", language=" + language + ", genre=" + genre + " ";
	}

	@Override
	public long getID() {
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

	@Override
	public List<Showtime> getShowtime() {
		return showtime;
	}

	@Override
	public void addShowtime(Showtime showtime) {
		if (this.showtime == null) {
			this.showtime = new ArrayList<Showtime>();
		}
		this.showtime.add(showtime);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieImpl other = (MovieImpl) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
