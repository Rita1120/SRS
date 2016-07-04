package dao;

import java.sql.SQLException;
import java.util.List;

import model.Student;
import model.TranscriptEntry;

public interface TranscriptEntryDao {
	public List<TranscriptEntry> findGrade(Student s) throws SQLException;
	public List<TranscriptEntry> findByGrade(TranscriptEntry TranscriptEntry) throws SQLException; 
	public List<TranscriptEntry> findBySno(String sno) throws SQLException; 
}

