package com.jz.filter;

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
import javax.servlet.http.HttpSession;

import com.jz.bean.User;

public class JspFilter implements Filter {

	private String redirectUrl;//跳转路径
	private String passUrl;//
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String servletpath = request.getServletPath();
		
		if (servletpath.equals(redirectUrl)) {
			
			Cookie[] cookies=request.getCookies();
			
			if (cookies!=null&&cookies.length>0) {
				//循环数组
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user")) {
						String value=cookie.getValue();
						String[] nv=value.split(",");
						User user=new User();
						user.setName(nv[0]);
						user.setPassword(nv[1]);
						HttpSession session=request.getSession();
						session.setAttribute("user", user);
						response.sendRedirect(basePath+"main/mainAction.action");
					}
				}
			}
			
			arg2.doFilter(request, response);
			return;
		}
		String[] passes=passUrl.split(",");
		for(String pass:passes){
			if(servletpath.equals(pass)){
				arg2.doFilter(request, response);
				return;
			}
		}
		
		//自动登录验证
		
		
		response.sendRedirect(basePath+redirectUrl);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		redirectUrl=arg0.getInitParameter("redirectUrl");
		passUrl=arg0.getInitParameter("passUrl");

	}

}
