package com.gezelbom.digiscore2;

import java.io.Serializable;

/**
 * Currently not fully implemented
 * @author Alex
 *
 */
public class GolfCourse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	public GolfCourse() {
	}
	
	public GolfCourse(String n) {
		name = n;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}

}
