package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.Collection;
import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.CollectionService;

/**
 * Servlet implementation class AddCollectionServlet
 */
@WebServlet("/AddCollectionServlet")
public class AddCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		User u = (User)request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		
		Collection c = new Collection();
		c.setUser(u.getName());
		c.setProductid(id);
		
		CollectionService service = new CollectionService();
		
		if(service.addCollection(c)){
			out.write("{\"errCode\":0,\"errMsg\":\"收藏成功^_^\"}");
		}else{
			out.write("{\"errCode\":1,\"errMsg\":\"收藏失败-_-\"}");
		}
		
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
