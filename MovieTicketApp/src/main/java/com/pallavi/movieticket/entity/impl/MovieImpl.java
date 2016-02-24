package com.pallavi.movieticket.entity.impl;

import com.pallavi.movieticket.entity.Movie;

public class MovieImpl implements Movie {

	String name;
	String language;
	String genre;
	

	public MovieImpl(String name, String language, String genre) {
		this.name = name;
		this.language = language;
		this.genre = genre;
	}
	
	public MovieImpl(String name){
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public String getLanguage() {

		return language;
	}

	public String getGenre() {

		return genre;
	}
	
	

	@Override
	public String toString() {
		return "name=" + name + ", language=" + language + ", genre=" + genre + "";
	}

}
