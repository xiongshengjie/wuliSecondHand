package cn.wuliSecondHand.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.UserService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User u = (User)request.getSession().getAttribute("user");
		String nickname = request.getParameter("name");
		String institute = request.getParameter("xueyuan");
		String grade = request.getParameter("grade");
		String classes = request.getParameter("classes");
		u.setNickname(nickname);
		u.setInstitute(institute);
		u.setGrade(grade);
		u.setClasses(classes);
		UserService service = new UserService();

		if (service.findUser(u.getName()) != null) {
			service.editUser(u);
			response.sendRedirect("pages\\personcenter.html");
			return;
		} else {
			service.addUser(u);
			response.sendRedirect("pages\\personcenter.html");
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
