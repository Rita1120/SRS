package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.ScheduleDao;
import model.Course;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import util.DBUtil;

public class ScheduleDaoImpl implements ScheduleDao {
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt;
	@Override
	public ScheduleOfClasses getScheduleOfClass(String semester) throws SQLException {
		ScheduleOfClasses ScheduleOfClasses = null;
		HashMap<String, Section> sections=new HashMap<>();
		String sql = "SELECT * FROM scheduleOfClasses WHERE semester=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, semester);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Course c = new Course();
			Section section=new Section();
			ScheduleOfClasses = new ScheduleOfClasses();
			ScheduleOfClasses.setSemester(rs.getString("semester"));
			c.setCourseNo(rs.getString("courseNo"));
			section.setSectionNo(rs.getString("sectionNo"));
			section.setRepresentedCourse(c);
			sections.put(section.getSectionNo(), section);
			ScheduleOfClasses.setSectionsOffered(sections);
		}
		
		System.out.println("sections  "+sections+"ScheduleOfClasses  "+ScheduleOfClasses);
		this.pstmt.close();
		return ScheduleOfClasses;
	}

	@Override
	public boolean addSchedule(ScheduleOfClasses scheduleOfClasses) throws SQLException {
		boolean flag = false;
		String sql = "INSERT INTO scheduleOfClasses(semester,sectionNo,courseNo) VALUES (?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, scheduleOfClasses.getSemester());
		this.pstmt.setString(2, scheduleOfClasses.getSectionsOffered().get("section").getSectionNo());
		this.pstmt.setString(3, scheduleOfClasses.getSectionsOffered().get("section").getRepresentedCourse().getCourseNo());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
			System.out.println("insert successfully!");
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public Boolean deleteSchedule(String sno) throws SQLException {
		boolean flag = false;
		String sql = "delete from scheduleOfClasses where sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, sno);
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public String findbySC(ScheduleOfClasses scheduleOfClasses) throws SQLException {
		String sql = "SELECT * FROM scheduleOfClasses WHERE semester=? and courseNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,scheduleOfClasses.getSemester());
		this.pstmt.setString(1,scheduleOfClasses.getSectionsOffered().get("section").getRepresentedCourse().getCourseNo());
		ResultSet rs = this.pstmt.executeQuery();
		String a="";
		if (rs.next()) {
		   a=rs.getString("sectionNo");
		}
		//System.out.println("sectionImpl:"+Section);
		this.pstmt.close();
		return a;
	}

	@Override
	public String findBySno(String sectionNo) throws SQLException {
		String sql = "SELECT courseNo FROM scheduleOfClasses WHERE sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,sectionNo);
		ResultSet rs = this.pstmt.executeQuery();
		String a="";
		if (rs.next()) {
		   a=rs.getString("courseNo");
		}
		//System.out.println("sectionImpl:"+Section);
		this.pstmt.close();
		return a;
	}

}
