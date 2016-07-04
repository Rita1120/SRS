package service;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import dao.CourseDao;
import dao.PersonDao;
import dao.ScheduleDao;
import dao.SectionDao;
import model.Course;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import model.User;
import sqliteDao.CourseDaoImpl;
import sqliteDao.PersonDaoImpl;
import sqliteDao.ScheduleDaoImpl;
import sqliteDao.SectionDaoImpl;

public class ScheduleOfClassesService {
	
    ScheduleDao ScheduleOfClassesDao=new ScheduleDaoImpl();
    SectionDao sectionDao=new SectionDaoImpl();
    CourseDao courseDao=new CourseDaoImpl();
    PersonDao personDao=new PersonDaoImpl();

    public ScheduleOfClassesService(){
   
    }
    
   public List<Section> getScheduleOfClass(String semester) throws SQLException {
    	ScheduleOfClasses ScheduleOfClasses=ScheduleOfClassesDao.getScheduleOfClass(semester);
    	HashMap<String, Section> sectionsOffered=ScheduleOfClasses.getSectionsOffered();
    	Section section=null;
    	List<Section> sections=new ArrayList<>();
    	for (Entry<String, Section> entry : sectionsOffered.entrySet()) {
    		section=sectionDao.findBySno(entry.getKey());
    		Course course=courseDao.findByNo(section.getRepresentedCourse().getCourseNo());
    		Professor professor=personDao.findBySsn(section.getInstructor().getSsn());
    		System.out.println(course.getCourseName()+course.getCredits());
    		section.setRepresentedCourse(course);
    		section.setInstructor(professor);
    		sections.add(section);
    	}
		return sections;
	}
    
    
    public boolean addScheduleOfClasses(ScheduleOfClasses ScheduleOfClasses) throws SQLException {
		return ScheduleOfClassesDao.addSchedule(ScheduleOfClasses);
	}
    
    public boolean deleteScheduleOfClasses(ScheduleOfClasses ScheduleOfClasses) throws SQLException {
  		return ScheduleOfClassesDao.deleteSchedule(ScheduleOfClasses.getSectionsOffered().get("section").getSectionNo())&&sectionDao.deleteSection(ScheduleOfClasses.getSectionsOffered().get("section").getSectionNo());
  	}
    
    public String findSno(ScheduleOfClasses ScheduleOfClasses) throws SQLException {
		return ScheduleOfClassesDao.findbySC(ScheduleOfClasses);
	}
    public String findBySno(String sectionNo) throws SQLException {
		return ScheduleOfClassesDao.findBySno(sectionNo);
	}
    

}
