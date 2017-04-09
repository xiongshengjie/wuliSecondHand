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

			String sql = "insert into user(name,password,nickname,wechat,qq,telnum,institute,classes,grade) values(?,?,?,?,?,?,?,?,?)";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			runner.update(sql, u.getName(),u.getPassword(),u.getNickname(),u.getWechat(),
					u.getQq(),u.getTelnum(),u.getInstitute(),u.getClasses(),u.getGrade());
		}
		
		// 修改用户信息
		public void editUser(User u) throws SQLException {

			List<Object> obj = new ArrayList<Object>();
			obj.add(u.getNickname());
			obj.add(u.getInstitute());
			obj.add(u.getClasses());
			obj.add(u.getGrade());
			obj.add(u.getName());
			String sql  = "update user set  nickname=?,institute=?,classes=? ,grade=? where name=?";

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
