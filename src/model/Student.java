package model;
// Student.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Student extends Person {
	//------------
	// Attributes.
	//------------

	private String major;
	private String degree;
	private Transcript transcript;
	private List<Section> attends; 



	public List<Section> getAttends() {
		return attends;
	}

	public void setAttends(List<Section> attends) {
		this.attends = attends;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}
	




	@Override
	public String toString() {
		return "Student [major=" + major + ", degree=" + degree + ", transcript=" + transcript + ", attends=" + attends
				+ "]";
	}

	public void addSection(Section s) {
		attends.add(s);
	}
	
	public void dropSection(Section s) {
		attends.remove(s);
	}


	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)) return true;
		else return false;
	}

	public boolean isCurrentlyEnrolledInSimilar(Section s1) {        //判断是否选过相同课
		boolean foundMatch = false;

		Course c1 = s1.getRepresentedCourse();

		for (Section s2 : attends) {
			Course c2 = s2.getRepresentedCourse();
			if (c1.getCourseNo() == c2.getCourseNo()) {
				if (s2.getGrade(this) == null) {
					foundMatch = true;
					break;
				}
			}
		}

		return foundMatch;
	}
		

	public Collection<Section> getEnrolledSections() {
		return attends;
	}
}
