package service;

import model.User;
import sqliteDao.UserDaoImpl;

import java.sql.SQLException;

import dao.UserDao;

public class UserService {
    UserDao userDao=new UserDaoImpl();
	public Boolean login(User user) throws SQLException {
		//System.out.println(user.getPassword()+" "+user.getSsn());
		//userDao.findBySsn("001");
		return userDao.findBySsn(user.getSsn()).getPassword().equals(user.getPassword())?true:false;
		
	}
	
}
