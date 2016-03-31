package com.pallavi.movieticket.entity;

import java.util.Date;

public interface Showtime {
	
	Date getShowtime();
	Movie getMovie();
	Theater getTheater();
	long getId();

}
