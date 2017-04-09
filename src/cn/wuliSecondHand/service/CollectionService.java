package cn.wuliSecondHand.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wuliSecondHand.dao.CollectionDao;
import cn.wuliSecondHand.dao.ProductDao;
import cn.wuliSecondHand.domain.Collection;
import cn.wuliSecondHand.domain.Product;

public class CollectionService {
	
	private CollectionDao dao = new CollectionDao();
	private ProductDao pDao = new ProductDao();
	
	public boolean addCollection(Collection c) {
		try {
			dao.addCollection(c);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加收藏失败");
			return false;
		}
		
	}
	
	public boolean delCollection(Collection c) {
		try {
			dao.delCollection(c);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("取消收藏失败");
			return false;
		}
	}
	
	@SuppressWarnings("null")
	public List<Product> findCollection(String name) {
		List<Collection> idList = null;
		List<Product> pList = new ArrayList<Product>();
		try {
			idList = dao.findCollection(name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取收藏列表失败");
		}
		
		if(idList == null){
			return null;
		}else{
			for(Collection c : idList){
				try {
					pList.add(pDao.findProductById(c.getProductid()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return pList;
		}
	}

}
