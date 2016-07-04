package model;
// EnrollmentStatus.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A SUPPORT enum.


// Used by the Section class to represent various possible outcomes of
// an attempt to enroll by a Student.

public enum EnrollmentStatus {
	// Enumerate the values that the enum can assume.
	success("选课成功"), 
	secFull("课程无容量"), 
	prereq("无先修"), 
	prevEnroll("已选过该课程");

	// This represents the value of an enum instance.
	private final String value;

	// A "constructor" of sorts (used above).
	EnrollmentStatus(String value) {
		this.value = value;
	}

	// Accessor for the value of an enum instance.
	public String value() {
		return value;
	}
}
