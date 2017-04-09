package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.UserService;
import cn.wuliSecondHand.utils.JsonUtils;

/**
 * Servlet implementation class findUserServlet
 */
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = (User)request.getSession().getAttribute("user");
		PrintWriter out = response.getWriter();
		UserService service = new UserService();
		
		User getu =  service.findUser(u.getName());

		
		if(getu == null){
			out.write("{\"errCode\":1,\"errMsg\":\"未设置个人信息-_-\"}");
			return;
		}else {
			out.write("{\"errCode\":0,\"user\":"+JsonUtils.toJson(getu)+"}");
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
