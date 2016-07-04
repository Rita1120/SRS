package model;
// Professor.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;

public class Professor extends Person {
	//------------
	// Attributes.
	//------------

	private String title;
	private String department;
	private ArrayList<Section> teaches; 

	//----------------
	// Constructor(s).
	//----------------


		
	//----------------
	// Accessor methods.
	//----------------

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}




	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return getName() + " (" + getTitle() + ", " +
		       getDepartment() + ")";
	}

	
	public void agreeToTeach(Section s) {
		teaches.add(s);

		// We need to link this bidirectionally.

		s.setInstructor(this);
	}
}
