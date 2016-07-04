package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.Professor;
import model.Section;

public interface SectionDao {

	public List<Section> findAll() throws SQLException;//找全部排课
	public boolean addSection(Section section) throws SQLException;
	public Boolean deleteSection(String no) throws SQLException;
	public Section findBySno(String sno) throws SQLException;
	public Boolean updateSection(Section Section) throws SQLException ;
}
