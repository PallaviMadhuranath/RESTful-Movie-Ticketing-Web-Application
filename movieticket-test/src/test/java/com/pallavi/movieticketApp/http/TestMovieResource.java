package com.pallavi.movieticketApp.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestMovieResource {

	private static final String HTTP_HOST = "http://localhost:8080";
	private static final String URI_PATH = "MovieTicketApp/rest/movies";

	private Client client = ClientBuilder.newClient();
	private WebTarget target;
	private HttpMovie movieToTest;

	@Before
	public void init() {
		target = client.target(HTTP_HOST).path(URI_PATH);
		movieToTest = new HttpMovie();
		movieToTest.name = "Avengers" + new Random().nextInt(99999);
		movieToTest.language = "English";
		movieToTest.genre = "Action";
	}
	@Test
	public void testDeleteMovie() {
		// Testing deleting of unknown resourse throws 404
		target = client.target(HTTP_HOST).path(URI_PATH + "/113423523115135123536363459");
		Response response = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(404, response.getStatus());
	}


	@Test
	public void testCreateAndDeleteByMovieId() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(movieToTest, MediaType.APPLICATION_JSON));

		HttpMovie createResponse = response.readEntity(HttpMovie.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, movieToTest.name);
		Assert.assertEquals(createResponse.language, movieToTest.language);
		Assert.assertEquals(createResponse.genre, movieToTest.genre);
		Assert.assertNotNull(createResponse.id);

		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}
	
	@Test
	public void testCreateAndGetByTheaterId() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(movieToTest, MediaType.APPLICATION_JSON));

		HttpMovie createResponse = response.readEntity(HttpMovie.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, movieToTest.name);
		Assert.assertEquals(createResponse.language, movieToTest.language);
		Assert.assertEquals(createResponse.genre, movieToTest.genre);
		Assert.assertNotNull(createResponse.id);

		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response getResponse = target.request().accept(MediaType.APPLICATION_JSON).get();

		HttpMovie getResultMovie = getResponse.readEntity(HttpMovie.class);
		Assert.assertEquals(getResultMovie.name, movieToTest.name);
		Assert.assertEquals(getResultMovie.language, movieToTest.language);
		Assert.assertEquals(getResultMovie.genre, movieToTest.genre);
		Assert.assertNotNull(getResultMovie.id);

		// clean up the created theater
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}
	
	@Test
	public void testCreateAndSearchByMovieName() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(movieToTest, MediaType.APPLICATION_JSON));

		HttpMovie createResponse = response.readEntity(HttpMovie.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, movieToTest.name);
		Assert.assertEquals(createResponse.language, movieToTest.language);
		Assert.assertEquals(createResponse.genre, movieToTest.genre);
		Assert.assertNotNull(createResponse.id);

		Response searchResponse = target.queryParam("movieName", movieToTest.name).request()
				.accept(MediaType.APPLICATION_JSON).get();

		String jsonStringResponse = searchResponse.readEntity(String.class);
		Gson gson = new Gson();

		@SuppressWarnings("serial")
		Type collectionType = new TypeToken<List<HttpMovie>>() {
		}.getType();
		List<HttpMovie> searchResultList = gson.fromJson(jsonStringResponse, collectionType);
		Assert.assertEquals(searchResultList.get(0), createResponse);

		// clean up the created theater
		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}


}
