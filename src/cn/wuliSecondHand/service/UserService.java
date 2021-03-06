package cn.wuliSecondHand.service;

import java.sql.SQLException;

import cn.wuliSecondHand.dao.UserDao;
import cn.wuliSecondHand.domain.User;

public class UserService {
	
	private UserDao dao = new UserDao();
	
	public void addUser(User u) {
		try {
			dao.addUser(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加用户失败");
		}
	}
	
	public void editUser(User u) {
		
		try {
			dao.editUser(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改用户失败");
		}
	}
	
	public User findUser(String name) {
		
		User u = null;
		try {
			u = dao.findUser(name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查找用户失败");
		}
		return u;
	}

}
