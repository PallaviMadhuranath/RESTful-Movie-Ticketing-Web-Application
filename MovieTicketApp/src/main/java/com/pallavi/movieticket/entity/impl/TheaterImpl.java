package com.pallavi.movieticket.entity.impl;

import com.pallavi.movieticket.entity.Theater;

public class TheaterImpl implements Theater {

	String name;
	String address;
	int availability;

	public TheaterImpl(String name, String address, int availability) {
		this.name = name;
		this.address = address;
		this.availability = availability;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getAvailability() {
		return availability;
	}

	@Override
	public String toString() {
		return "name=" + name + ", address=" + address + ", availability=" + availability + "";
	}

}
