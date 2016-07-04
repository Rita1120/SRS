package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.Course;
import model.Professor;

public interface CourseDao {
	
	public HashMap<String, Course> findAll()throws SQLException;
	public Course findByNo(String CourseNo) throws SQLException;//通过课程编号找
    public List<Course> findPre(String courseNo) throws SQLException;//找先修
}
