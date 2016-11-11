package com.jz.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.jz.bean.User;
import com.jz.dao.UserDAO;
import com.jz.service.LoginService;
import com.jz.service.LoginServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private LoginService service;
	
	
	public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	private User user;
	
	private Integer auto_load;
	
//	private String username;//用户名
//	private String password;//密码
	


	private int log_res;//注册结果
	
	private Map session;
	
	@Override
	public String execute() throws Exception {
//		User user=new User();
//		
//		user.setName(username);
//		user.setPassword(password);
		
		if(auto_load!=null&&auto_load==1){
			Cookie cookie=new Cookie("user", user.getName()+","+user.getPassword());
			//设置cookie过期时间 单位秒
			cookie.setMaxAge(60*60);
			cookie.setPath("/");
			HttpServletResponse	response=ServletActionContext.getResponse();
			response.addCookie(cookie);
		}
		
	    log_res=service.login(user);
		if(log_res==0){
			
			if(user.getName().equals("admin")){
				session.put("user", user);
				 return "log3";
			}else
			session.put("user", user);
		        return "log1";
		  }else
		    	return "input";
		  
		  
	}

	public Integer getAuto_load() {
		return auto_load;
	}

	public void setAuto_load(Integer auto_load) {
		this.auto_load = auto_load;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public int getLog_res() {
		return log_res;
	}

	public void setLog_res(int log_res) {
		this.log_res = log_res;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}

	
	
}
