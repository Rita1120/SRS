package model;
// Section.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.HashMap;

public class Section {
	//------------
	// Attributes.
	//------------

	private String sectionNo;
	private String dayOfWeek;
	private String timeOfDay;
	private String room;
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	private int seatingCapacity;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;

	private HashMap<String, Student> enrolledStudents; 

	public HashMap<String, Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(HashMap<String, Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	private HashMap<Student, TranscriptEntry> assignedGrades; 

	public void setTimeOfDay(String time) {
		timeOfDay = time;
	}
	
	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setInstructor(Professor prof) {
		instructor = prof;
	}
	
	public Professor getInstructor() {
		return instructor;
	}
	
	public void setRepresentedCourse(Course c) {
		representedCourse = c;
	}
	
	public Course getRepresentedCourse() {
		return representedCourse;
	}		

	public void setRoom(String r) {
		room = r;
	}

	public String getRoom() {
		return room;
	}

	public void setSeatingCapacity(int c) {
		seatingCapacity = c;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setOfferedIn(ScheduleOfClasses soc) {
		offeredIn = soc;
	}

	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}	

	public EnrollmentStatus enroll(Student s) {

		Transcript transcript = s.getTranscript();

		if (s.isCurrentlyEnrolledInSimilar(this) || 
		    transcript.verifyCompletion(this.getRepresentedCourse())) {
			return EnrollmentStatus.prevEnroll;
		}

		Course c = this.getRepresentedCourse();
		if (c.hasPrerequisites()) {
			for (Course pre : c.getPrerequisites()) {
				if (transcript.verifyCompletion(pre)) {
					return EnrollmentStatus.prereq;
				}
				else{
					return EnrollmentStatus.secFull;
				}
			}
		}
		
		if (!this.confirmSeatAvailability()) {
			return EnrollmentStatus.secFull;
		}

		enrolledStudents.put(s.getSsn(), s);
		s.addSection(this);

		return EnrollmentStatus.success;
	}
	


	private boolean confirmSeatAvailability() {
		if (enrolledStudents.size() < getSeatingCapacity()) return true;
		else return false;
	}

	public boolean drop(Student s) {
		

		if (!s.isEnrolledIn(this)) return false;
		else {
			enrolledStudents.remove(s.getSsn());
			s.dropSection(this);
			return true;
		}
	}

	public int getTotalEnrollment() {
		return enrolledStudents.size();
	}	

	
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	public String getSectionNo() {
		return sectionNo;
	}





	public String getGrade(Student s) {
		String grade = null;
		TranscriptEntry te = assignedGrades.get(s);
		if (te != null) {
			grade = te.getGrade(); 
		}


		return grade;
	}

	public boolean postGrade(Student s, String grade) {


		if (!TranscriptEntry.validateGrade(grade)) return false;
		if (assignedGrades.get(s) != null) return false;

		TranscriptEntry te = new TranscriptEntry(s, grade, this);


		assignedGrades.put(s, te);

		return true;
	}
	
	public boolean isSectionOf(Course c) {
		if (c == representedCourse) return true;
		else return false;
	}

	@Override
	public String toString() {
		return "Section [sectionNo=" + sectionNo + ", dayOfWeek=" + dayOfWeek + ", timeOfDay=" + timeOfDay + ", room="
				+ room + ", seatingCapacity=" + seatingCapacity + ", representedCourse=" + representedCourse
				+ ", offeredIn=" + offeredIn + ", instructor=" + instructor + ", enrolledStudents=" + enrolledStudents
				+ ", assignedGrades=" + assignedGrades + "]";
	}

	


}
