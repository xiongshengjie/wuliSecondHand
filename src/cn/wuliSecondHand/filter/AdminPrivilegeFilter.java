package cn.wuliSecondHand.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.utils.HttpClientUtils;

public class AdminPrivilegeFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 1 强制转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 2判断是否具有权限
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			// 3.放行
			chain.doFilter(request, response);
			return;
		} else {
			Cookie[] cookie = request.getCookies();
			if (cookie != null) {
				String name = null;
				String password = null;
				for (int i = 0; i < cookie.length; i++) {
					if (cookie[i].getName().equals("name")) {
						name = cookie[i].getValue();
					}
					if (cookie[i].getName().equals("password")) {
						password = cookie[i].getValue();
					}
				}
				if (name != null && password != null) {
					boolean flag = HttpClientUtils.httpPostRequest("http://sso.jwc.whut.edu.cn/Certification/login.do?"
							+"systemId=&xmlmsg=&userName="+name+"&password="+password+"&type=xs&imageField.x=60&imageField.y=20");
					if(flag){
						user = new User();
						user.setName(name);
						user.setPassword(password);
						request.getSession().setAttribute("user", user);
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}

		response.sendRedirect(request.getContextPath() + "/pages/login.html");

	}

	public void destroy() {

	}

}
