package com.pallavi.movieticketApp.http;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.SystemPropertyUtils;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestMovieResource {
	private static final String HTTP_HOST = "http://localhost:8080";
	private static final String URI_PATH = "MovieTicketApp/rest/movies";
	//private static final String PATH_PARAM = "1";
	
	private Client client = ClientBuilder.newClient();
	private WebTarget target;
	
	@Before
	public void init(){
		target = client.target(HTTP_HOST).path(URI_PATH);
	}
	/*@Test
	public void testGetMoviesNoParamsJson(){						
		Response response =	target.request().accept(MediaType.APPLICATION_JSON).get();
		checkStatus(response);
		
	}*/
	
	@Test
	public void testGetMovieParamsJson(){
		Response response =	target.request().accept(MediaType.APPLICATION_JSON).get();
		verifyNotFound(response);
	}
	
	private void verifyNotFound(Response response) {
		//HttpError error = response.readEntity(HttpError.class);
		System.out.println(response.getStatus());
		Assert.assertEquals(404, response.getStatus());
		//Assert.assertEquals(404, error.status);
	}
	/*@Test
	public void testGetMovieParamsJson(){
		
		HttpMovie movie = new HttpMovie();
		movie.name = "God Father";
		movie.language = "English";
		movie.genre = "Drama";
		

		Response response = target.queryParam("title",movie.name).request().accept(MediaType.APPLICATION_JSON).get();
		
		HttpMovie createResponse = response.readEntity(HttpMovie.class);
		
		//List<HttpMovie> movieSearch = response.readEntity(new GenericType<List<HttpMovie>>(){});
		//System.out.println(movieSearch);
		
	}*/
	/**
	 * Check for status of 200.
	 * @param response - has the response from the server in Json form.
	 */
	private void checkStatus(Response response) {
		
		List<HttpMovie> searchResponse = response.readEntity(new GenericType<List<HttpMovie>>(){});
		//System.out.println(searchResponse.size());	
		Assert.assertEquals(200,response.getStatus());	
		Assert.assertEquals(2,searchResponse.size());	
		Assert.assertEquals("English",searchResponse.get(0).language);
		Assert.assertNotEquals("French", searchResponse.get(0).language);
		System.out.println(searchResponse.get(1).name.length() < 15);
		Assert.assertTrue(searchResponse.get(1).name.length() < 15);
	}

}
