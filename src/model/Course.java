package model;
// Course.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Course {
	//------------
	// Attributes.
	//------------

	private String courseNo;
	private String courseName;
	private double credits;
	private List<Section> offeredAsSection; 
	private List<Course> prerequisites; 
	
	//----------------
	// Constructor(s).
	//----------------

	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);

		// Note that we're instantiating empty support Collection(s).

		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}
	public void setPrerequisites(List<Course> pre) {
		this.prerequisites = pre;
	}

	public Course(){
		
	}

	//------------------
	// Accessor methods.
	//------------------

	public void setCourseNo(String cNo) {
		courseNo = cNo;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	
	public void setCourseName(String cName) {
		courseName = cName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCredits(double c) {
		credits = c;
	}
	
	public double getCredits() {
		return credits;
	}
	
	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	
	
	@Override
	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) return true;
		else return false;
	}


//	public Section scheduleSection(char day, String time, String room,
//				       int capacity) {
//		// Create a new Section (note the creative way in
//		// which we are assigning a section number) ...
//
//		Section s = new Section(offeredAsSection.size() + 1, 
//				day, time, this, room, capacity);
//		
//		// ... and then remember it!
//
//		addSection(s);
//		
//		return s;
//	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}
	public void addSection(Section s) {
		offeredAsSection.add(s);
	}
}
