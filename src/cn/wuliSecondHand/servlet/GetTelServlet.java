package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.exception.FindProductByIdException;
import cn.wuliSecondHand.service.ProductService;
import cn.wuliSecondHand.utils.JsonUtils;

/**
 * Servlet implementation class GetTelServlet
 */

public class GetTelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			out.write("{\"errCode\":1,\"errMsg\":\"没有登录\"}");
			return;
		}else{
			String id = request.getParameter("id");
			
			ProductService service = new ProductService();
			
			try {
				// 调用service层方法，通过id查找商品
				Product p = service.findProductById(id);
				String json = JsonUtils.toJson(p);
				out.write("{\"errCode\":0,\"data\":"+json+"}");

			} catch (FindProductByIdException e) {
				e.printStackTrace();
			}
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
