package com.pallavi.movieticket.entity.impl;

import com.pallavi.movieticket.entity.Theater;

/**
 * This class implements Theater interface. All the methods are defined in this
 * class
 * 
 * @author pallavidas
 *
 */
public class TheaterImpl implements Theater {

	String name;
	String address;

	/**
	 * Constructor initialization.
	 * 
	 * @param name
	 *            - theater name
	 * @param address
	 *            - theater address
	 */
	public TheaterImpl(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "name=" + name + ", address=" + address + "";
	}

}
