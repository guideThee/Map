package com.c.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**由于UserInterceptor没法过滤不放在WEB-INF的jsp文件，因此采用最原始的Filter过滤器
 * @author Administrator
 *
 */
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(req.getSession().getAttribute("USER") == null) {
			resp.setContentType("text/json; charset=utf-8");
			resp.getWriter().write("您尚未登录");
		}else {
			chain.doFilter(request, response);
		}


	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
