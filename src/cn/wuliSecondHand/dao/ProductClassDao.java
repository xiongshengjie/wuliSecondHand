package cn.wuliSecondHand.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.ProductClass;
import cn.wuliSecondHand.utils.DataSourceUtils;

public class ProductClassDao {

		// 查找所有商品
		public List<ProductClass> listAll() throws SQLException {
			String sql = "select * from class";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<ProductClass>(ProductClass.class));
		}
}
