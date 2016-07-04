package service;
import model.Course;
import sqliteDao.CourseDaoImpl;

import java.sql.SQLException;

import dao.CourseDao;

public class CourseService {

	CourseDao CourseDao=new CourseDaoImpl();
	
//	public CourseService(CourseDao dao) throws SQLException{
//		this.courseDao = dao;
//		courses = new CourseCatalog(dao.findAll());
//	}

//	public CourseCatalog getCourseCatalog() {
//		return courses;
//	}
//	
//	public Course findCourse(String courseNo){
//	   return courses.findCourse(courseNo);
//	} 
//	 
	public Course findByNo(String courseNo) throws SQLException{
		   return CourseDao.findByNo(courseNo);
    }
	

}
