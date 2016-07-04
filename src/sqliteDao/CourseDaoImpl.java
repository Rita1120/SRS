package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import dao.CourseDao;
import model.Course;
import model.Professor;
import util.DBUtil;

public class CourseDaoImpl implements CourseDao {
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt = null;
	

	@Override
	public Course findByNo(String CourseNo) throws SQLException {
		Course Course = null;
		String sql = "SELECT * FROM course WHERE courseNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, CourseNo); 
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Course = new Course();
			Course.setCourseNo(rs.getString("courseNo"));
			Course.setCourseName(rs.getString("courseName"));
			Course.setCredits(rs.getInt("credits"));
		}
        //System.out.println("courseno:"+Course.getCourseNo()+"coursename"+Course.getCourseName());
		this.pstmt.close();
		return Course;
	}

	@Override
	public HashMap<String, Course> findAll() throws SQLException {
		HashMap<String,Course> courses = new HashMap<String, Course>();
		String sql = "SELECT * FROM course";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		Course c = null;
		Professor p = new Professor();
		while (rs.next()) {
			c = new Course();
			c.setCourseNo(rs.getString("courseNo"));
			c.setCourseName(rs.getString("courseName"));
			c.setCredits(rs.getInt("credits"));
			courses.put(c.getCourseNo(), c);
		}
		this.pstmt.close();
		return courses;
	}

	@Override
	public List<Course> findPre(String courseNo) throws SQLException {
		List<Course> courses=new ArrayList<>();
		String sql = "SELECT * FROM preRequisites where courseNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, courseNo); 
		ResultSet rs = this.pstmt.executeQuery();
		Course c = null;
		Professor p = new Professor();
		while (rs.next()) {
			c = new Course();
			c.setCourseNo(rs.getString("preCourseNo"));
			courses.add(c);
		}
		this.pstmt.close();
		return courses;
		
	}

	



}
