package model;
// Transcript.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.



import java.util.List;

public class Transcript {

	public List<TranscriptEntry> getTranscriptEntries() {
		return transcriptEntries;
	}


	public void setTranscriptEntries(List<TranscriptEntry> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}

	private List<TranscriptEntry> transcriptEntries; 
	

	private Student studentOwner;



	public Transcript() {
		
	}


	public void setStudentOwner(Student s) {
		studentOwner = s;
	}

	public Student getStudentOwner() {
		return studentOwner;
	}

	public boolean verifyCompletion(Course c) {
		boolean outcome = false;

		for (TranscriptEntry te : transcriptEntries) {
			Section s = te.getSection();

			if (s.isSectionOf(c)) {
			    if (TranscriptEntry.passingGrade(te.getGrade())) {
				outcome = true;

				break;
			    }
			}
		}

		return outcome;
	}

	public void addTranscriptEntry(TranscriptEntry te) {
		transcriptEntries.add(te);
	}


	@Override
	public String toString() {
		return "Transcripttostring [transcriptEntries=" + transcriptEntries + ", studentOwner=" + studentOwner + "]";
	}


	

}
