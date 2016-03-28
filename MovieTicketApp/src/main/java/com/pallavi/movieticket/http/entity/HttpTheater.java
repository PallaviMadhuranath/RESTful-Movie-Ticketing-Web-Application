package com.pallavi.movieticket.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pallavi.movieticket.entity.Theater;

@XmlRootElement(name = "theater")
public class HttpTheater {
	
	@XmlElement
	public long id;

	@XmlElement
	public String name;

	@XmlElement
	public String city;
	
	@XmlElement
	public String zipCode;

	protected HttpTheater() {
	}

	public HttpTheater(Theater theater) {
		
		this.id = theater.getID();
		this.name = theater.getName();
		this.city = theater.getCity();
		this.zipCode = theater.getZipCode();

	}

}
