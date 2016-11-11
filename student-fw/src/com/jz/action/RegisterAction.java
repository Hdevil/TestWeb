package com.jz.action;

import com.jz.bean.User;
import com.jz.service.LoginService;
import com.jz.service.LoginServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	private LoginService service;
	
	private String email;//邮箱地址
	private String username;//用户名
	private String password;//密码
	private String password2;//密码
	private int reg_res;//注册结果
	
	
	
	public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("email="+email+";"+"username="+username+";"+"password="+password);
		User user=new User();
		
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		
		reg_res=service.register(user);
		
		
		return super.execute();
		
	}
	
	public int getReg_res() {
		return reg_res;
	}

	public void setReg_res(int reg_res) {
		this.reg_res = reg_res;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	

	
}
