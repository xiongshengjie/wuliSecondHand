package cn.wuliSecondHand.service;

import java.sql.SQLException;
import java.util.List;

import cn.wuliSecondHand.dao.ProductDao;
import cn.wuliSecondHand.dao.UserDao;
import cn.wuliSecondHand.domain.DetailBean;
import cn.wuliSecondHand.domain.PageBean;
import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.exception.AddProductException;
import cn.wuliSecondHand.exception.FindProductByIdException;
import cn.wuliSecondHand.exception.ListProductException;

public class ProductService {

	private ProductDao dao = new ProductDao();

	// 添加商品
	public void addProduct(Product p) throws AddProductException {

		try {
			dao.addProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddProductException("添加商品失败");
		}
	}

	// 查找所有商品信息
	public List<Product> listAll() throws ListProductException {
		try {
			return dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("查询商品失败");
		}
	}

	// 分页操作
	public PageBean findProductByPage(int currentPage, int currentCount,
			String category, String username) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);

		// 封装当前查找类别
		bean.setCategory(category);
		
		bean.setUsername(username);

		try {
			// 获取当前页数据
			List<Product> ps = dao.findByPage(currentPage, currentCount,
					category,username);
			bean.setPs(ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	// 根据id查找商品
	public DetailBean findProductById(String id) throws FindProductByIdException {
		Product p = new Product();
		DetailBean d = new DetailBean();
		try {
			p = dao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindProductByIdException("根据ID查找商品失败");
		}
		if(p!=null){
			UserDao udao = new UserDao();
			User u = new User();
			try {
				u = udao.findUser(p.getUser());
				if(u==null){
					u.setName(p.getUser());
				}
				u.setPassword(null);
				u.setQq(null);
				u.setWechat(null);
				u.setTelnum(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			d.setProduct(p);
			d.setUser(u);
		}
		return d;
	}

	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice) {
		List<Product> ps = null;
		try {
			ps = dao.findProductByManyCondition(id, name, category, minprice,
					maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

	// 修改商品信息
	public void editProduct(Product p) {
		try {
			dao.editProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//前台，用于搜索框根据书名来模糊查询相应的图书
	public PageBean findBookByName(int currentPage, int currentCount,
			String searchfield) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);
		// 封装模糊查询的图书名
		bean.setSearchfield(searchfield);
		try {
			
			List<Product> ps = dao.findBookByName(currentPage,currentCount,searchfield);
			bean.setPs(ps);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败！");
		}
	}

	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) {
		try {
			dao.deleteProduct(id);
		} catch (SQLException e) {
			throw new RuntimeException("后台系统根据id删除商品信息失败！");
		}
	}
}
