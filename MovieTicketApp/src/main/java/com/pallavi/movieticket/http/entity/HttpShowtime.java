package com.pallavi.movieticket.http.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pallavi.movieticket.entity.Showtime;

@XmlRootElement(name = "showtime")
public class HttpShowtime {

	@XmlElement
	public long showtimeId;
	@XmlElement
	private long theatreId;
	@XmlElement
	private String theatreName;
	@XmlElement
	private Date time;
	@XmlElement
	private String movieName;
	@XmlElement
	private long movieId;

	// required by framework
	protected HttpShowtime() {
	}

	public HttpShowtime(Showtime showtime) {
		this.showtimeId = showtime.getId();
		this.time = showtime.getShowtime();
		this.movieId = showtime.getMovie().getID();
		this.movieName = showtime.getMovie().getName();
		this.theatreId = showtime.getTheater().getID();
		this.theatreName = showtime.getTheater().getName();

	}

}
