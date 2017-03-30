package cn.wuliSecondHand.service;

import java.sql.SQLException;
import java.util.List;

import cn.wuliSecondHand.dao.ProductClassDao;
import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.ProductClass;
import cn.wuliSecondHand.exception.ListProductException;

public class ProductClassService {
	
	ProductClassDao dao = new ProductClassDao();

	public List<ProductClass> listAll() throws ListProductException {
		try {
			return dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("获取列表失败");
		}
	}
}
