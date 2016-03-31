package com.pallavi.movieticketApp.http;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestTheaterResource {

	private static final String HTTP_HOST = "http://localhost:8080";
	private static final String URI_PATH = "MovieTicketApp/rest/theaters";

	private Client client = ClientBuilder.newClient();
	private WebTarget target;
	private HttpTheater theaterToTest;

	@Before
	public void init() {
		target = client.target(HTTP_HOST).path(URI_PATH);
		theaterToTest = new HttpTheater();
		theaterToTest.name = "AMC" + new Random().nextInt(99999);
		theaterToTest.city = "Sunnyvale" + new Random().nextInt(99999);
		theaterToTest.zipCode = "9087";
	}

	@Test
	public void testDeleteTheater() {
		// Testing deleting of unknown resourse throws 404
		target = client.target(HTTP_HOST).path(URI_PATH + "/113423523115135123536363459");
		Response response = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(404, response.getStatus());
	}

	@Test
	public void testCreateAndDeleteByTheaterId() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(theaterToTest, MediaType.APPLICATION_JSON));

		HttpTheater createResponse = response.readEntity(HttpTheater.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, theaterToTest.name);
		Assert.assertEquals(createResponse.city, theaterToTest.city);
		Assert.assertEquals(createResponse.zipCode, theaterToTest.zipCode);
		Assert.assertNotNull(createResponse.id);

		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}

	@Test
	public void testCreateAndGetByTheaterId() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(theaterToTest, MediaType.APPLICATION_JSON));

		HttpTheater createResponse = response.readEntity(HttpTheater.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, theaterToTest.name);
		Assert.assertEquals(createResponse.city, theaterToTest.city);
		Assert.assertEquals(createResponse.zipCode, theaterToTest.zipCode);
		Assert.assertNotNull(createResponse.id);

		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response getResponse = target.request().accept(MediaType.APPLICATION_JSON).get();

		HttpTheater getResultTheater = getResponse.readEntity(HttpTheater.class);
		Assert.assertEquals(getResultTheater.name, theaterToTest.name);
		Assert.assertEquals(getResultTheater.city, theaterToTest.city);
		Assert.assertEquals(getResultTheater.zipCode, theaterToTest.zipCode);
		Assert.assertNotNull(getResultTheater.id);

		// clean up the created theater
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}

	@Test
	public void testCreateAndSearchByTheaterName() {
		// create theater
		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(theaterToTest, MediaType.APPLICATION_JSON));

		HttpTheater createResponse = response.readEntity(HttpTheater.class);
		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(createResponse.name, theaterToTest.name);
		Assert.assertEquals(createResponse.city, theaterToTest.city);
		Assert.assertNotNull(createResponse.id);

		Response searchResponse = target.queryParam("theaterName", theaterToTest.name).request()
				.accept(MediaType.APPLICATION_JSON).get();

		String jsonStringResponse = searchResponse.readEntity(String.class);
		Gson gson = new Gson();

		@SuppressWarnings("serial")
		Type collectionType = new TypeToken<List<HttpTheater>>() {
		}.getType();
		List<HttpTheater> searchResultList = gson.fromJson(jsonStringResponse, collectionType);
		Assert.assertEquals(searchResultList.get(0), createResponse);

		// clean up the created theater
		target = client.target(HTTP_HOST).path(URI_PATH + "/" + createResponse.id);
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_JSON).delete();
		Assert.assertEquals(200, deleteResponse.getStatus());
	}

}
