package com.jz.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;




import com.jz.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AutoInterceptor extends AbstractInterceptor{

	
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//获取全部cookie数据
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
//							if (request.getServletPath().equals("/index.jsp")) {
//								return "mainAction";
//							}
						}
					}
				}
		
		return arg0.invoke();
	}

	

	

	

}
