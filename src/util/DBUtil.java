package util;

import java.sql.*;
import java.io.*;

public class DBUtil {
	private static final long serialVersionUID = 1L;
	static Connection conn;
	public static Connection getConnection(){
		String driver="org.sqlite.JDBC";
		String conStr="jdbc:sqlite:D:\\WorkSpace\\SRS\\data\\srs.db";
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(conStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;			
	}
	
}
