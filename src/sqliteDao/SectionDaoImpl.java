package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SectionDao;
import model.Course;
import model.Professor;
import model.Section;
import util.DBUtil;

public class SectionDaoImpl implements SectionDao{
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt;
	@Override
	public List<Section> findAll() throws SQLException {
		List<Section> results = new ArrayList<Section>();
		String sql = "select * from section";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		Section Section = null;
		
		while (rs.next()) {
			Course c = new Course();
			Professor p = new Professor();
			Section = new Section();
			Section.setSectionNo(rs.getString("sectionNo"));
			Section.setDayOfWeek(rs.getString("dayOfWeek"));
			Section.setTimeOfDay(rs.getString("timeOfDay"));
			Section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			Section.setRoom(rs.getString("room"));
			c.setCourseNo(rs.getString("courseNo"));
			Section.setRepresentedCourse(c);
			p.setSsn(rs.getString("professor"));
			Section.setInstructor(p);
			results.add(Section);
		}
		this.pstmt.close();
		System.out.println(results);
		return results;
	}


	@Override
	public boolean addSection(Section section) throws SQLException {
		boolean flag = false;
		String sql = "INSERT INTO section(sectionNo,dayOfWeek,timeOfDay,room,seatingCapacity,professor,courseNo) VALUES (?,?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, section.getSectionNo());
		this.pstmt.setString(2, section.getDayOfWeek());
		this.pstmt.setString(3, section.getTimeOfDay());
		this.pstmt.setString(4,section.getRoom());
		this.pstmt.setInt(5,section.getSeatingCapacity());
		this.pstmt.setString(6,section.getInstructor().getSsn());
		this.pstmt.setString(7,section.getRepresentedCourse().getCourseNo());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
			System.out.println("insert successfully!");
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public Boolean deleteSection(String no) throws SQLException {
		boolean flag = false;
		String sql = "delete from section where sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, no);
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public Section findBySno(String sno) throws SQLException {
		Section Section = null;
		String sql = "SELECT * FROM section WHERE sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, sno);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Course c = new Course();
			Professor p = new Professor();
			Section = new Section();
			Section.setSectionNo(rs.getString("sectionNo"));
			Section.setDayOfWeek(rs.getString("dayOfWeek"));
			Section.setTimeOfDay(rs.getString("timeOfDay"));
			Section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			Section.setRoom(rs.getString("room"));
			c.setCourseNo(rs.getString("courseNo"));
		    //c.setCourseName("DXYY2");
			Section.setRepresentedCourse(c);
			p.setSsn(rs.getString("professor"));
			Section.setInstructor(p);
		}
		//System.out.println("sectionImpl:"+Section);
		this.pstmt.close();
		return Section;
	}

	@Override
	public Boolean updateSection(Section Section) throws SQLException {
		boolean flag = false;
		String sql = "update section set dayOfWeek=?,timeOfDay=?,room=?,seatingCapacity=?,professor=? where sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,Section.getDayOfWeek());
		this.pstmt.setString(2, Section.getTimeOfDay());
		this.pstmt.setString(3,Section.getRoom());
		this.pstmt.setInt(4,Section.getSeatingCapacity());
		this.pstmt.setString(5,Section.getInstructor().getSsn());
		this.pstmt.setString(6,Section.getSectionNo());
		System.out.println(sql);
		if (this.pstmt.executeUpdate()>0) {
			flag = true;
		}
		System.out.println("flag"+flag);
		this.pstmt.close();
		return flag;
	}

}
