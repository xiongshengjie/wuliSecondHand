package cn.wuliSecondHand.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.wuliSecondHand.domain.DetailBean;
import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.exception.FindProductByIdException;
import cn.wuliSecondHand.service.ProductService;
import cn.wuliSecondHand.utils.JsonUtils;

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
		
		ProductService service = new ProductService();
		
		try {
			// 调用service层方法，通过id查找商品
			DetailBean d = service.findProductById(id);
			Product p = d.getProduct();
			
			p.setQq(null);
			p.setWechat(null);
			p.setTelnum(null);
			String json = JsonUtils.toJson(d);
			response.getWriter().write(json);

		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
