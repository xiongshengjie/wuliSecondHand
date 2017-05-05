package cn.wuliSecondHand.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

//import com.sun.faces.taglib.jsf_core.MaxMinValidatorTag;

import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.utils.DataSourceUtils;

public class ProductDao {
	// 添加商品
	public void addProduct(Product p) throws SQLException {

		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getId(), p.getTitle(), p.getPrice(),
				p.getCategory(), p.getImgurl(), p.getImgurlcompress(),p.getUser(),p.getDescription(), p.getSchoolarea(), p.getIsbargain(),p.getWechat(),p.getQq(),p.getTelnum(), p.getIschange(),new Timestamp(System.currentTimeMillis()),p.getFlag());
	}

	// 查找所有商品
	public List<Product> listAll() throws SQLException {
		String sql = "select * from product";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	// 获取数据总条数
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from product";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (!"全部商品".equals(category)) {
			sql += " where category=?";

			Long count = (Long) runner
					.query(sql, new ScalarHandler(), category);

			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());

			return count.intValue();
		}

	}

	// 获取当前页数据
	public List<Product> findByPage(int currentPage, int currentCount,
			String category,String username ,String world) throws SQLException {
		// 要执行的sql语句
		String sql = null;
		// 参数
		Object[] obj = null;
		// 如果category不为null,代表是按分类查找
		if (!"全部商品".equals(category)) {
			sql = "select * from product  where category=? ";
			obj = new Object[] { category, (currentPage - 1) * currentCount,
					currentCount, };
			if("world".equals(world)){
				sql += " AND flag!=0 order by pushtime desc limit ?,? ";
			}else{
				sql += " AND flag=0 order by pushtime desc limit ?,? ";
			}
		} else {
			if(username==null){
				sql = "select * from product where 1=1  ";
				obj = new Object[] { (currentPage - 1) * currentCount,
						currentCount, };
				if("world".equals(world)){
					sql += " AND flag!=0 order by pushtime desc limit ?,? ";
				}else{
					sql += " AND flag=0 order by pushtime desc limit ?,? ";
				}
			}else{
				sql = "select * from product where user=? order by pushtime desc limit ?,? ";
				obj = new Object[] { username,(currentPage - 1) * currentCount,
						currentCount, };
			}
		}
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				obj);
	}

	// 根据id查找商品
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from product where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice)
			throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from product where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and id=?";
			list.add(id);
		}

		if (name != null && name.trim().length() > 0) {
			sql += " and name=?";
			list.add(name);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice != null && maxprice != null
				&& minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
			sql += " and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}

		Object[] params = list.toArray();

		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				params);
	}

	// 修改商品信息
	public void editProduct(Product p) throws SQLException {

		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getTitle());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getDescription());
		String sql  = "update products set  title=?,price=? ,category=?,description=? ";
		if (p.getImgurl() != null && p.getImgurl().trim().length() > 0) {
			sql += " ,imgurl=?";
			obj.add(p.getImgurl());
		}
		sql += " where id=?";
		obj.add(p.getId());
		
		System.out.println(sql);
		
		System.out.println(obj);

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, obj.toArray());

	}

	//前台，用于搜索框根据书名来模糊查询相应的商品
	public List<Product> findProductByName(int currentPage, int currentCount,
			String searchfield , String world) throws SQLException {
		//根据名字模糊查询图书
		String sql = "SELECT * FROM product WHERE title LIKE '%"+searchfield+"%'  ";
		if("world".equals(world)){
			sql  += " AND flag!=0 LIMIT ?,?";
		}else{
			sql += " AND flag=0 LIMIT ?,?";
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//用于分页查询的数据
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
		return runner.query(sql, 
				new BeanListHandler<Product>(Product.class),currentPage-1,currentCount);
	}

	//前台搜索框，根据书名模糊查询出的图书总数量
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		String sql = "SELECT COUNT(*) FROM product WHERE title LIKE '%"+searchfield+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//查询出满足条件的总数量，为long类型
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) throws SQLException {
		String sql = "DELETE FROM product WHERE id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
