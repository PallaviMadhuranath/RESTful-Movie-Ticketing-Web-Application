package com.pallavi.movieticketApp.http;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "theater")
public class HttpTheater {

	@XmlElement
	public String id;

	@XmlElement
	public String name;

	@XmlElement
	public String city;

	@XmlElement
	public String zipCode;

	@Override
	public String toString() {
		return "HttpTheater [id=" + id + ", name=" + name + ", city=" + city + ", zipCode=" + zipCode + "]";
	}

}
