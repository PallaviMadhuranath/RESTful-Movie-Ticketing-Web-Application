package com.pallavi.movieticket.entity.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Theater;

/**
 * This class implements Theater interface. All the methods are defined in this
 * class
 * 
 * @author pallavidas
 *
 */
@Entity
@Table(name = "theatres")
public class TheaterImpl implements Theater {

	@Id
	@Column(name = "Theatre_ID")
	private String id;

	@Column(name = "Name")
	String name;

	@Column(name = "City")
	String city;
	
	@Column(name = "zipCode")
	String zipCode;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "theaters", targetEntity = MovieImpl.class)
	private List<Movie> movies;

	public TheaterImpl() {

	}

	/**
	 * Constructor initialization.
	 * 
	 * @param name
	 *            - theater name
	 * @param address
	 *            - theater address
	 */
	public TheaterImpl(String id,String name, String address, String zipCode) {
		this.id = id;
		this.name = name;
		this.city = address;
		this.zipCode = zipCode;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "name=" + name + ", city=" + city + ", zipcode=" + zipCode + "";
	}

	@Override
	public String getID() {
		return id;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	/*public void addMovie(Movie movie) {
		if (movies == null) {
			movies = new ArrayList<Movie>();
		}
		movies.add(movie);
	}*/

}
