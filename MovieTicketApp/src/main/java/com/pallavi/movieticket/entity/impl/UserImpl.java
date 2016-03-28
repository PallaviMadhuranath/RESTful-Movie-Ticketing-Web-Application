package com.pallavi.movieticket.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.User;

@Entity
@Table(name = "users")
public class UserImpl implements User {

	@Id // primary key
	@Column(name = "idusers")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "password")
	private String password;

	/*@OneToMany(mappedBy = "user", targetEntity = Movie.class, cascade = CascadeType.ALL)
	private List<Movie> movies;*/

	public UserImpl() {
	}

	@Override
	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {

		return lastName;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", password=" + password + "]";
	}

	/*@Override
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public void addUserMovie(Movie movie) {
		if (this.movies == null) {
			this.movies = new ArrayList<Movie>();
		}
		this.movies.add(movie);

	}*/

}
