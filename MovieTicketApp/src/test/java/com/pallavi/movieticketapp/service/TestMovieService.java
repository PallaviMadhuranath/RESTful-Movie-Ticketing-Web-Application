package com.pallavi.movieticketapp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.service.impl.MovieServiceImpl;


public class TestMovieService{
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
	
	public List<Movie> movieList = new ArrayList<Movie>();
	
	@Test
	public void testGetAllMovies(){
		MovieServiceImpl ms = (MovieServiceImpl) context.getBean("movieServiceImpl");
		System.out.println(ms.getAllMovies());
		
	}
	@Test
	public void testGetMovieByNamePositive(){
		MovieServiceImpl ms = (MovieServiceImpl) context.getBean("movieServiceImpl");
		Movie movie = ms.getMovieByName("Titanic");
		System.out.println(movie);
		Assert.assertEquals("Titanic",movie.getName());
		Assert.assertEquals("English",movie.getLanguage());
		Assert.assertEquals("Romance",movie.getGenre());
	}
	
	@Test
	public void testGetMovieByNameNegative(){
		MovieServiceImpl ms = (MovieServiceImpl) context.getBean("movieServiceImpl");
		Movie movie = ms.getMovieByName("Deadpool");
		System.out.println(movie);
		Assert.assertNull(movie);
	}
	

}
