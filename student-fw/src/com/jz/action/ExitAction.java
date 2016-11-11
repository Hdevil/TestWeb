package com.jz.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport {

	private HttpServletRequest request;
	private HttpServletResponse response;
	@Override
	public String execute() throws Exception {

		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		//删除cookie中的user对象
		Cookie cookie=new Cookie("user", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "success";
	}

	
	
}
