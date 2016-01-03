package com.gezelbom.digiscore2;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Simple DTO to store and transfer all the objects and values required for a
 * round.
 */
public class RoundDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	FriendDTO playerOne;
	FriendDTO playerTwo;
	FriendDTO playerThree;
	FriendDTO playerFour;
	GolfCourse course;
	int holes;
	Calendar date;

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	int year;
	int month;
	int day;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public RoundDTO() {
		// TODO Auto-generated constructor stub
	}

	public FriendDTO getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(FriendDTO playerOne) {
		this.playerOne = playerOne;
	}

	public FriendDTO getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(FriendDTO playerTwo) {
		this.playerTwo = playerTwo;
	}

	public FriendDTO getPlayerThree() {
		return playerThree;
	}

	public void setPlayerThree(FriendDTO playerThree) {
		this.playerThree = playerThree;
	}

	public FriendDTO getPlayerFour() {
		return playerFour;
	}

	public void setPlayerFour(FriendDTO playerFour) {
		this.playerFour = playerFour;
	}

	public GolfCourse getCourse() {
		return course;
	}

	public void setCourse(GolfCourse course) {
		this.course = course;
	}

	public int getHoles() {
		return holes;
	}

	public void setHoles(int holes) {
		this.holes = holes;
	}

}
