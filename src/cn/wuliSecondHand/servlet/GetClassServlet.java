package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.ProductClass;
import cn.wuliSecondHand.exception.ListProductException;
import cn.wuliSecondHand.service.ProductClassService;
import cn.wuliSecondHand.utils.JsonUtils;

/**
 * Servlet implementation class GetClassServlet
 */
public class GetClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<ProductClass> cla = new ArrayList<ProductClass>();
		
		ProductClassService service = new ProductClassService();
		
		try {
			cla = service.listAll();
		} catch (ListProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = JsonUtils.toJSONString(cla);
		
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
