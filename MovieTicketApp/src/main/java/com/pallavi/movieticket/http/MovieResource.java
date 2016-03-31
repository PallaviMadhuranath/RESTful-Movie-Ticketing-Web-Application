package com.pallavi.movieticket.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Hibernate;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pallavi.movieticket.entity.Movie;
import com.pallavi.movieticket.entity.Showtime;
import com.pallavi.movieticket.entity.impl.MovieImpl;
import com.pallavi.movieticket.http.entity.HttpMovie;
import com.pallavi.movieticket.http.entity.HttpShowtime;
import com.pallavi.movieticket.service.MovieService;
import com.pallavi.movieticket.service.exception.InvalidFieldException;
//specifies a relative path for the resource
@Path("/movies")
@Component
// while sending a request client-agent tells server how it is sending the
// request. Here it is in JSON format
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
// server tells the client-agent how it is responding to the request. Here it is
// JSON
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MovieResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieService movieService;

	@POST
	@Path("/")
	public Response createMovie(HttpMovie newMovie) {
		Movie movieCreate = convert(newMovie);
		Movie addedMovie = movieService.addMovie(movieCreate);
		return Response.status(Status.CREATED).header("Location", "/movies/" + addedMovie.getID())
				.entity(new HttpMovie(addedMovie)).build();
	}

	@GET
	@Path("/{movieId}")
	@Wrapped(element = "movies")
	public Response getMovieByName(@PathParam("movieId") long id) {
		logger.info("movie id:", id);
		Movie movie = movieService.getMovieById(id);

		if (movie == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Movie not found for movieId: " + id).build();
		}
		Hibernate.initialize(movie);
		Hibernate.initialize(movie.getShowtime());
		HttpMovie httpMovie = new HttpMovie(movie);

		Iterator<Showtime> iterator = movie.getShowtime().iterator();
		while (iterator.hasNext()) {
			Showtime showtime = iterator.next();
			httpMovie.getShowtimes().add(new HttpShowtime(showtime));
		}
		return Response.ok(httpMovie).build();
	}

	@GET
	@Path("/")
	@Wrapped(element = "movies")
	public Response getMoviesSearch(@QueryParam("movieName") String movieName,
			@QueryParam("theaterName") String theaterName) throws InvalidFieldException {
		System.out.println("ENteres");
		List<Movie> movieFound = new ArrayList<Movie>();

		if (movieName != null && !movieName.trim().equals("")) {
			movieFound = movieService.getMoviesByName(movieName);
		} else if (theaterName != null && !theaterName.trim().equals("")) {
			movieFound = movieService.getMovieByTheater(theaterName);
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Exepting movieName or theaterName as query param").build();
		}

		List<HttpMovie> returnList = new ArrayList<>(movieFound.size());
		for (Movie movie : movieFound) {
			returnList.add( new HttpMovie(movie));
		}
		
		String jsonResponse = new Gson().toJson(returnList);
		return Response.ok(jsonResponse).build();
	}

	@DELETE
	@Path("/{movieId}")
	@Wrapped(element = "movies")
	public Response deleteMovie(@PathParam("movieId") long id) {
		Movie movie = movieService.getMovieById(id);
		if (movie == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Movie not found for movieId: " + id).build();
		}

		// deleting the movie
		movieService.deleteMovie(id);

		return Response.ok(new HttpMovie(movie)).build();
	}

	private Movie convert(HttpMovie httpMovie) {
		MovieImpl movie = new MovieImpl();
		movie.setName(httpMovie.name);
		movie.setLanguage(httpMovie.language);
		movie.setGenre(httpMovie.genre);
		return movie;
	}

}
