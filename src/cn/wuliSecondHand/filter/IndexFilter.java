package cn.wuliSecondHand.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.utils.HttpClientUtils;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter("/IndexFilter")
public class IndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IndexFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
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
				User user = new User();
				user.setName(name);
				user.setPassword(password);
				request.getSession().setAttribute("user", user);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
