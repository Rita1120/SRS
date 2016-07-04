package service;


import java.sql.SQLException;
import java.util.List;

import dao.TranscriptEntryDao;
import model.Student;
import model.TranscriptEntry;
import sqliteDao.TranscriptEntryDaoImpl;

public class TranscriptEntryService {
	TranscriptEntryDao transcriptEntryDao=new TranscriptEntryDaoImpl();
	
	public List<TranscriptEntry> findGrade(Student student) throws SQLException{
		return transcriptEntryDao.findGrade(student);
	}
	
	public List<TranscriptEntry> findByGrade(TranscriptEntry TranscriptEntry)throws SQLException{
		return transcriptEntryDao.findByGrade(TranscriptEntry);
	}
	public List<TranscriptEntry> findBySno(String sno)throws SQLException{
		return transcriptEntryDao.findBySno(sno);
	}
}
