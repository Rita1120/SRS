package dao;

import java.sql.SQLException;
import java.util.List;

import model.Professor;
import model.Student;

public interface PersonDao {
	
	public List<Professor> findAllProfessors() throws SQLException;//找出所有教师
	public Boolean addProfessors(Professor professor) throws SQLException;//新增教师
	public Boolean deletePerson(String ssn) throws SQLException;
	public List<Professor> findProfessors(Professor professor) throws SQLException;//查询教师
	public Professor findBySsn(String ssn) throws SQLException;  
	public Boolean updateProfessor(Professor professor) throws SQLException;
	public Student findByStuSsn(String ssn) throws SQLException;//查询学生
}
