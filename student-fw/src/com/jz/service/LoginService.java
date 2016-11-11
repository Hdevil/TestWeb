package com.jz.service;

import com.jz.bean.User;






/*
 * 登录注册服务接口
 */

public interface LoginService {

	//判断是否注册成功  0-注册成功 1-以后以存在
		int register(User user);
		//判断是否登录成功  0-登录成功 1-用户不存在 2-密码不正确
		int login(User user);
		//
		/**
		 * 用户登录
		 * @param username 
		 * @param password
		 * @return 当前登录用户对象
		 */
		User login(String username,String password);
	
	
}
