package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TranscriptEntryDao;

import model.Section;
import model.Student;
import model.TranscriptEntry;
import util.DBUtil;

public class TranscriptEntryDaoImpl implements TranscriptEntryDao {
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt = null;
	@Override
	public List<TranscriptEntry> findGrade(Student s) throws SQLException {
		List<TranscriptEntry> tes=new ArrayList<>();
		String sql = "SELECT * FROM transcript where ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, s.getSsn()); 
		ResultSet rs = this.pstmt.executeQuery();
		TranscriptEntry TranscriptEntry = null;
		Section section=new Section();
		while (rs.next()) {
			TranscriptEntry = new TranscriptEntry();
			TranscriptEntry.setStudent(s);
			TranscriptEntry.setGrade(rs.getString("grade"));
			section.setSectionNo(rs.getString("sectionNo"));
			TranscriptEntry.setSection(section);
			tes.add(TranscriptEntry);
		}
		this.pstmt.close();
		System.out.println(tes);
		return tes;
	}

	@Override
	public List<TranscriptEntry> findByGrade(TranscriptEntry TranscriptEntry) throws SQLException {
		List<TranscriptEntry> tes=new ArrayList<>();
		String sql = "SELECT * FROM transcript where grade=? and ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		//System.out.println(TranscriptEntry.getStudent().getSsn());
		this.pstmt.setString(1, TranscriptEntry.getGrade()); 
		this.pstmt.setString(2, TranscriptEntry.getStudent().getSsn()); 
		ResultSet rs = this.pstmt.executeQuery();
		TranscriptEntry te = null;
		while (rs.next()) {
			Section section=new Section();
			te = new TranscriptEntry();
			Student student=new Student();
			student.setSsn(TranscriptEntry.getStudent().getSsn());
			TranscriptEntry.setStudent(student);
			TranscriptEntry.setGrade(rs.getString("grade"));
			section.setSectionNo(rs.getString("sectionNo"));
			TranscriptEntry.setSection(section);
			tes.add(te);
		}
		System.out.println(tes);
		this.pstmt.close();
		return tes;
	}

	@Override
	public List<TranscriptEntry> findBySno(String sno) throws SQLException {
		List<TranscriptEntry> tes=new ArrayList<>();
		String sql = "SELECT * FROM transcript where sectionNo=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,sno); 
		ResultSet rs = this.pstmt.executeQuery();
		TranscriptEntry TranscriptEntry = null;
		Section section=new Section();
		while (rs.next()) {
			TranscriptEntry = new TranscriptEntry();
			Student student=new Student();
		    student.setSsn(rs.getString("ssn"));
		    TranscriptEntry.setStudent(student);
			TranscriptEntry.setGrade(rs.getString("grade"));
			section.setSectionNo(rs.getString("sectionNo"));
			TranscriptEntry.setSection(section);
			tes.add(TranscriptEntry);
		}
		this.pstmt.close();
		System.out.println(tes);
		return tes;
	}

}
