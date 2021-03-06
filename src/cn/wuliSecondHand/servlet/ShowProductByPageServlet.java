package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.PageBean;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.ProductService;
import cn.wuliSecondHand.utils.JsonUtils;

//分页显示数据
public class ShowProductByPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.定义当前页码，默认为1
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.定义每页显示条数,默认为6
		int currentCount = 6;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}

		// 3.获取查找的分类
		String category = "全部商品";

		String _category = request.getParameter("category");

		if (_category != null && !"".equals(category)) {
			category = _category;
		}
		
		String username = null;
		
		if (request.getParameter("type")!=null) {
			User user = (User) request.getSession().getAttribute("user");
			username = user.getName();
		}
		
		String condition = request.getParameter("condition");
		
		String world = request.getParameter("hello");
		
		ProductService service = new ProductService();
		PageBean bean = null;
		
		if(!(condition==null || "".equals(condition))){
			bean = service.findProductByName(currentPage, currentCount, condition , world);
		}else{
			// 4.调用service，完成获取当前页分页Bean数据.
			bean = service.findProductByPage(currentPage, currentCount,
					category,username , world);
		}

		// 将数据存储到request范围，跳转到index.html页面展示
		String json = JsonUtils.toJson(bean);
		
		PrintWriter out = response.getWriter();
		
		out.write(json);
		
		/*request.setAttribute("bean", bean);

		request.getRequestDispatcher("index.html").forward(request, response);
		return;*/
		return;

	}

}
