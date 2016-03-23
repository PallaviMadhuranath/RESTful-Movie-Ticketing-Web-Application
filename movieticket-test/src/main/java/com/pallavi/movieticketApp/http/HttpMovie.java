package com.pallavi.movieticketApp.http;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movie")
public class HttpMovie {
	
	@XmlElement
	public String id;

	@XmlElement
	public String name;

	@XmlElement
	public String language;

	@XmlElement
	public String genre;

	@Override
	public String toString() {
		return "HttpMovie [id=" +id + ", name=" + name + ", language=" + language + ", genre=" + genre + "]";
	}

}
