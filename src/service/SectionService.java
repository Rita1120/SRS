package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.CourseDao;
import dao.SectionDao;
import dao.TranscriptEntryDao;
import model.Course;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import sqliteDao.CourseDaoImpl;
import sqliteDao.SectionDaoImpl;
import sqliteDao.TranscriptEntryDaoImpl;

public class SectionService {
    SectionDao sectionDao=new SectionDaoImpl();
    TranscriptEntryDao transcriptEntryDao=new TranscriptEntryDaoImpl();
    CourseDao courseDao=new CourseDaoImpl();
    public List<Section> findAll() throws SQLException {
		return sectionDao.findAll();
	}
    public boolean addSection(Section section) throws SQLException {
		return sectionDao.addSection(section);
	}
    
    public Section findBySno(String sno) throws SQLException {
		return sectionDao.findBySno(sno);
	}
    public boolean updateSection(Section section) throws SQLException {
		return sectionDao.updateSection(section);
	}
    
    public Section initSection(String sectionNo) throws SQLException{
    	Section section=sectionDao.findBySno(sectionNo);
//    	Transcript transcript =new Transcript();
//    	List<TranscriptEntry> transcriptEntries=transcriptEntryDao.findBySno(sectionNo);
//    	transcript.setTranscriptEntries(transcriptEntries);
//    	System.out.println("transcriptEntries.toString()"+transcriptEntries.toString());
//    	System.out.println("transcript.toString()"+transcript.toString());
//    		
//		List<Student> ss=new ArrayList<>();
//		HashMap<String, Student> enrollStus=new HashMap<>();
//		
//		for (Iterator<TranscriptEntry> iterator = transcriptEntries.iterator(); iterator.hasNext();) {
//			TranscriptEntry transcriptEntry = (TranscriptEntry) iterator.next();
//			ss.add(transcriptEntry.getStudent());
//		}				
//		for (Iterator<Student> iterator = ss.iterator(); iterator.hasNext();) {
//			Student student = (Student) iterator.next();
//			enrollStus.put(student.getSsn(),student);
//		}		
//		
//		section.setEnrolledStudents(enrollStus);
    	List<Course> pre=courseDao.findPre(section.getRepresentedCourse().getCourseNo());
    	System.out.println("pre"+pre.toString());
    	section.getRepresentedCourse().setPrerequisites(pre);
        System.out.println("initsection"+section);
		return section;
		
    }
}
