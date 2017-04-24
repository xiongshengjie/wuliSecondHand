package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.utils.HttpClientUtils;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		boolean flag = HttpClientUtils.httpPostRequest("http://sso.jwc.whut.edu.cn/Certification/login.do?"
		+"systemId=&xmlmsg=&userName="+userName+"&password="+password+"&type=xs&imageField.x=60&imageField.y=20");
		if(flag){
			User user = new User();
			user.setName(userName);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			Cookie cookie = new Cookie("name", userName);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			cookie = new Cookie("password", password);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			
			out.write("true");
			return;
		}
		else{
			out.print("false");
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
