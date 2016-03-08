package com.pallavi.movieticket.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pallavi.movieticket.entity.Theater;

/**
 * This class implements Theater interface. All the methods are defined in this
 * class
 * 
 * @author pallavidas
 *
 */
@Entity
@Table(name = "theatres")
public class TheaterImpl implements Theater {
	
	@Id
	@Column(name = "Theatre_ID")
	private int id;
	
	@Column(name = "Name")
	String name;
	
	@Column(name = "Address")
	String address;
	
	public TheaterImpl(){
		
	}

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
	
	
	@Override
	public int getID() {
		return id;
	}

}
