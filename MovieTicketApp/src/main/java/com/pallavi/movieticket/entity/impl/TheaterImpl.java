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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Showtime;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Name")
	String name;

	@Column(name = "City")
	String city;

	@Column(name = "zipCode")
	String zipCode;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "theaters", targetEntity = MovieImpl.class)
	private List<Movie> movies;

	@OneToMany(mappedBy = "theater", targetEntity = ShowtimeImpl.class, cascade = CascadeType.ALL)
	private List<Showtime> showtime;

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
	public TheaterImpl(long id, String name, String address, String zipCode) {
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
	public long getID() {
		return id;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setId(long id) {
		this.id = id;
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
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((movies == null) ? 0 : movies.hashCode());
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
		TheaterImpl other = (TheaterImpl) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		if (movies == null) {
			if (other.movies != null)
				return false;
		} else if (!movies.equals(other.movies))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
