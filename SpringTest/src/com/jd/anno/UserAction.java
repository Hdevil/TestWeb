package com.jd.anno;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class UserAction {

	@Resource
	private UserService userService;
	
	
	public void execute(){
		System.out.println("UserAction.execute....");
		userService.doAdd();
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
