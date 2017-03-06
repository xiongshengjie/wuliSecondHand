package cn.wuliSecondHand.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;

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
		HttpClientUtils client = new HttpClientUtils();
		Map<String, Object> userpass = new HashMap<String, Object>();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		/*userpass.put("systemId", "");
		userpass.put("xmlmsg", "");
		userpass.put("name", "test");
		userpass.put("password", "123");
		userpass.put("type", "xs");
		userpass.put("imageField.x", "66");
		userpass.put("imageField.y", "22");*/
		//String flag = client.httpPostRequest("http://sso.jwc.whut.edu.cn/Certification/login.do", userpass);
		boolean flag = client.httpPostRequest("http://sso.jwc.whut.edu.cn/Certification/login.do?"
		+"systemId=&xmlmsg=&userName="+userName+"&password="+password+"&type=xs&imageField.x=60&imageField.y=20");
		if(flag){
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
