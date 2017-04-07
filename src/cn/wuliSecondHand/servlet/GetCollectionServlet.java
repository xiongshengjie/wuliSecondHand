package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.CollectionService;
import cn.wuliSecondHand.utils.JsonUtils;

/**
 * Servlet implementation class GetCollection
 */
public class GetCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		User u = (User)request.getSession().getAttribute("user");
		CollectionService service = new CollectionService();
		List<Product> list = service.findCollection(u.getName());
		
		String json = JsonUtils.toJSONString(list);
		out.write(json);
		
		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
