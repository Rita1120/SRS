package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import dao.CourseDao;
import dao.PersonDao;
import dao.SectionDao;
import dao.TranscriptEntryDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import sqliteDao.CourseDaoImpl;
import sqliteDao.PersonDaoImpl;
import sqliteDao.SectionDaoImpl;
import sqliteDao.TranscriptEntryDaoImpl;

public class PersonService {
	PersonDao personDao=new PersonDaoImpl();
	TranscriptEntryDao transcriptEntryDao=new TranscriptEntryDaoImpl();
	SectionDao sectionDao=new SectionDaoImpl();
	CourseDao courseDao=new CourseDaoImpl();
	ScheduleOfClassesService scheduleOfClassesService=new ScheduleOfClassesService();

	public List<Professor> showProfessor() throws SQLException{
		return personDao.findAllProfessors();
	}
	
	public Boolean addProfessor(Professor professor) throws SQLException {
		return personDao.addProfessors(professor);
	}
	public Boolean deletePerson(String ssn) throws SQLException {
		return personDao.deletePerson(ssn);
	}

	public List<Professor> findProfessor(Professor professor) throws SQLException {
		return personDao.findProfessors(professor);
	}
	public Student findByStuSsn(String ssn) throws SQLException {
		return personDao.findByStuSsn(ssn);
	}
	public Professor findBySsn(String ssn) throws SQLException {
		return personDao.findBySsn(ssn);
	}
	
	public Boolean updateProfessor(Professor professor) throws SQLException {
		return personDao.updateProfessor(professor);
	}
	public Student initStudent(String ssn) throws SQLException{
		Student student=personDao.findByStuSsn(ssn);
	    List<TranscriptEntry> tes=transcriptEntryDao.findGrade(student);//找成绩单
	    Transcript transcript=new Transcript();
	    transcript.setTranscriptEntries(tes);
	    List<Section> sections=new ArrayList<>();  //选过的课
	    for(TranscriptEntry te:tes){
	    	Course c=new Course();
	    	c.setCourseNo(scheduleOfClassesService.findBySno(te.getSection().getSectionNo()));
	    	te.getSection().setRepresentedCourse(c);
	    	sections.add(te.getSection());
	    	System.out.println("init c "+c.getCourseNo());
	    }
	    
	    student.setAttends(sections);
		return student;
		
	}
	
	
}