package dao;

import java.sql.SQLException;

import model.ScheduleOfClasses;
import model.Section;

public interface ScheduleDao {
	
	public ScheduleOfClasses getScheduleOfClass(String semester) throws SQLException;
	public boolean addSchedule(ScheduleOfClasses scheduleOfClasses) throws SQLException;
	public Boolean deleteSchedule(String sno) throws SQLException;
	public String findbySC(ScheduleOfClasses scheduleOfClasses) throws SQLException;
	public String findBySno(String sectionNo) throws SQLException;
}
