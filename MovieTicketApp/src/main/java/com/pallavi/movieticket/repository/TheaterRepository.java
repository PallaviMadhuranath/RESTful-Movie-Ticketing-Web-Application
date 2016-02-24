package com.pallavi.movieticket.repository;

import java.util.List;

import com.pallavi.movieticket.entity.Theater;

public interface TheaterRepository {
	List<Theater> getAllTheaters();
	Theater getTheaterByName(String name);
}
