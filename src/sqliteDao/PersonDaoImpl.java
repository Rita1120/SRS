package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.PersonDao;
import model.Professor;
import model.Student;
import util.DBUtil;

public class PersonDaoImpl implements PersonDao{
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt;
	@Override
	public List<Professor> findAllProfessors() throws SQLException {
		List<Professor> results = new ArrayList<Professor>();
		String sql = "select * from person where type=3";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		Professor professor = null;
		while (rs.next()) {
			professor = new Professor();
		    professor.setSsn(rs.getString("ssn"));
		    professor.setName(rs.getString("name"));
		    professor.setTitle(rs.getString("title"));
		    professor.setDepartment(rs.getString("department"));
			results.add(professor);
		}
		this.pstmt.close();
		return results;
	}
	
	@Override
	public Boolean addProfessors(Professor professor) throws SQLException {
		boolean flag = false;
		String sql = "INSERT INTO person(ssn,name,password,title,department,type) VALUES (?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, professor.getSsn());
		this.pstmt.setString(2, professor.getName());
		this.pstmt.setString(3, "123");//原始密码为123
		this.pstmt.setString(4,professor.getTitle());
		this.pstmt.setString(5,professor.getDepartment());
		this.pstmt.setInt(6,3);
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
			System.out.println("insert successfully!");
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public Boolean deletePerson(String ssn) throws SQLException {
		boolean flag = false;
		String sql = "delete from person where ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, ssn);
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<Professor> findProfessors(Professor professor) throws SQLException {
		List<Professor> results = new ArrayList<Professor>();
		String sql = "select * from person where title like ? and department like ? and type=3";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" +professor.getTitle()+ "%");
		this.pstmt.setString(2, "%" +professor.getDepartment()+ "%");
		ResultSet rs = this.pstmt.executeQuery();
		Professor pro = null;
		while (rs.next()) {
			pro = new Professor();
			pro.setSsn(rs.getString("ssn"));
			pro.setName(rs.getString("name"));
			pro.setTitle(rs.getString("title"));
			pro.setDepartment(rs.getString("department"));
			results.add(pro);
		}
		System.out.println(results);
		this.pstmt.close();
		return results;
	}

	@Override
	public Professor findBySsn(String ssn) throws SQLException {
		Professor Professor = null;
		String sql = "SELECT * FROM person WHERE ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, ssn);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Professor = new Professor();
			Professor.setSsn(rs.getString("ssn"));
			Professor.setName(rs.getString("name"));
		    Professor.setTitle(rs.getString("title"));
		    Professor.setDepartment(rs.getString("department"));
		}
		System.out.println(Professor);
		this.pstmt.close();
		return Professor;
	}

	@Override
	public Boolean updateProfessor(Professor professor) throws SQLException {
		boolean flag = false;
		String sql = "update person set name=?, title=?,department=? where ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, professor.getName());
		this.pstmt.setString(2, professor.getTitle());
		this.pstmt.setString(3, professor.getDepartment());
		this.pstmt.setString(4, professor.getSsn());
		if (this.pstmt.executeUpdate()>0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public Student findByStuSsn(String ssn) throws SQLException {
		Student Student = null;
		String sql = "SELECT * FROM person WHERE ssn=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, ssn);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Student = new Student();
			Student.setSsn(rs.getString("ssn"));
			Student.setName(rs.getString("name"));
			Student.setMajor(rs.getString("major"));
			Student.setDegree(rs.getString("degree"));
		}
		this.pstmt.close();
		return Student;
	}

}
