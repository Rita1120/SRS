package sqliteDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import model.User;
import util.DBUtil;

public class UserDaoImpl implements UserDao{
	
	Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt;
	
	@Override
	public User findBySsn(String ssn) throws SQLException {
		
		User user=null;
		String sql1 = "select * from person where ssn=?";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, ssn);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()){
			user = new User();
			user.setSsn(rs.getString("ssn"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			//System.out.println(rs.getString("ssn")+"  "+rs.getString("password"));
		}
		this.pstmt.close();
		
		return user;
	
	}

}
