package cn.wuliSecondHand.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.wuliSecondHand.domain.Collection;
import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.utils.DataSourceUtils;

public class CollectionDao {
	
	public void addCollection(Collection c) throws SQLException {

		String sql = "insert into collection(user,productid) values(?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, c.getUser(),c.getProductid());
	}
	
	public List<Collection> findCollection(String name) throws SQLException {
		String sql = "select * from collection where user=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Collection>(Collection.class),name);
	}
	
	public void delCollection(Collection c) throws SQLException {
		
		String sql = "delete from collection where user=? and productid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, c.getUser(), c.getProductid());
	}
	
	public Collection isCollection(User user , String id) throws SQLException {
		String sql = "select * from collection where user=? and productid=?";
		Object[] obj = new Object[] { user.getName(),id };
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Collection>(Collection.class),obj);
		
	}

}
