package com.pallavi.movieticket.entity.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Showtime;
import com.pallavi.movieticket.entity.Theater;

@Entity
@Table(name = "showtime")
public class ShowtimeImpl implements Showtime {

	@Id
	@Column(name = "showtime_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "showtime_datetime")
	private Date showtimeDatetime;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = MovieImpl.class)
	@JoinColumn(name = "movies_movie_id")
	private Movie movie;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TheaterImpl.class)
	@JoinColumn(name = "theatres_theatre_id")
	private Theater theater;

	@Override
	public Date getShowtime() {
		return showtimeDatetime;
	}

	public void setShowtime(Date showtimeDatetime) {
		this.showtimeDatetime = showtimeDatetime;
	}

	@Override
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	@Override
	public Theater getTheater() {
		return theater;
	}

	public long getId() {
		return id;
	}

}
