package cn.wuliSecondHand.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.utils.DataSourceUtils;

public class UserDao {
	
		// 编辑个人信息
	public void addUser(User u) throws SQLException {

		String sql = "insert into user(name,password,nickname,wechat,qq,telnum,institute,classes,grade,job_intention,ex_jobinfo,resume) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, u.getName(), u.getPassword(), u.getNickname(), u.getWechat(), u.getQq(), u.getTelnum(),
				u.getInstitute(), u.getClasses(), u.getGrade(), u.getJob_intention(), u.getEx_jobinfo(), u.getResume());
	}
		
		// 修改用户信息
	public void editUser(User u) throws SQLException {

		List<Object> obj = new ArrayList<Object>();
		String sql = null;
		obj.add(u.getNickname());
		obj.add(u.getTelnum());
		obj.add(u.getInstitute());
		obj.add(u.getClasses());
		obj.add(u.getGrade());
		obj.add(u.getJob_intention());
		obj.add(u.getEx_jobinfo());

		if (u.getResume() != null && !"".equals(u.getResume())) {
			obj.add(u.getResume());
			sql = "update user set nickname=?,telnum=?,institute=?,classes=?,grade=?,job_intention=?,ex_jobinfo=?,resume=? where name=?";
		} else {
			sql = "update user set nickname=?,telnum=?,institute=?,classes=?,grade=?,job_intention=?,ex_jobinfo=? where name=?";
		}
		obj.add(u.getName());

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, obj.toArray());

	}
		
		// 返回用户信息
	public User findUser(String name) throws SQLException {
		String sql = "select * from user where name=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), name);
	}


}
