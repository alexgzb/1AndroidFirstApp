package com.gezelbom.digiscore2;

import java.io.Serializable;

/**
 * Simple DTO to store values regarding a Friend/Player
 * @author Alex
 *
 */
public class FriendDTO implements Serializable, Comparable<FriendDTO>{
	
	private static final long serialVersionUID = 1L;
	String name;
	String mail;
	int hcp;

	
	public FriendDTO(String name, String mail, int hcp) {
		
		this.name = name;
		this.mail = mail;
		this.hcp  = hcp;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public double getHcp() {
		return hcp;
	}


	public void setHcp(int hcp) {
		this.hcp = hcp;
	}


	/**
	 * CompareTo method that uses names for sorting 
	 */
	@Override
	public int compareTo(FriendDTO another) {
		
		return this.name.compareTo(another.name);
	}
	
	
}
