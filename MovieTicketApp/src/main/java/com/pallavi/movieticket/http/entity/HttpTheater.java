package com.pallavi.movieticket.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pallavi.movieticket.entity.Theater;

@XmlRootElement(name = "theater")
public class HttpTheater {

	@XmlElement
	public String name;

	@XmlElement
	public String address;

	protected HttpTheater() {
	}

	public HttpTheater(Theater theater) {
		this.name = theater.getName();
		this.address = theater.getAddress();

	}

}
