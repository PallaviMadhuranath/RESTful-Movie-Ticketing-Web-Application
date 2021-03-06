package com.pallavi.movieticket.http.entity;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pallavi.movieticket.entity.Movie;

@XmlRootElement(name = "movie")
public class HttpMovie {
	
	@XmlElement
	public long id;

	@XmlElement
	public String name;

	@XmlElement
	public String language;

	@XmlElement
	public String genre;
	
	@XmlElement
	private List<HttpShowtime> showtimes;
	
	//@XmlElement
	//List<Theater> thearers;

	// required by framework
	protected HttpMovie() {
	}

	public HttpMovie(Movie movie) {
		
		this.id = movie.getID();
		this.name = movie.getName();
		this.language = movie.getLanguage();
		this.genre = movie.getGenre();
		//this.thearers = movie.getTheaters();
	}

	public List<HttpShowtime> getShowtimes() {
		if(showtimes == null) {
			showtimes = new ArrayList<>();
		}
		return showtimes;
	}

}
