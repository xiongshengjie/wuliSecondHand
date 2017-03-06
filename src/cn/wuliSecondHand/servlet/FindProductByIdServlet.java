package cn.wuliSecondHand.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.exception.FindProductByIdException;
import cn.wuliSecondHand.service.ProductService;

/**
 * 根据商品id查找指定商品信息的servlet
 */
public class FindProductByIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品的id
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别普通用户和超级用户
		String type = request.getParameter("type");
		
		ProductService service = new ProductService();
		
		try {
			// 调用service层方法，通过id查找商品
			Product p = service.findProductById(id);
			request.setAttribute("p", p);
			// 普通用户默认不传递type值，会跳转到搜索结果页面
			if (type == null) {
				request.getRequestDispatcher("/searchlist.html").forward(request,response);
				return;
			}
			
			
			request.getRequestDispatcher("").forward(request, response);	//后台管理商品页面
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
