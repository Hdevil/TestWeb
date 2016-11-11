package com.jz.service;


import java.sql.Connection;

import com.jz.bean.User;
import com.jz.dao.BlackDAO;
import com.jz.dao.UserDAO;



/*
 * 登录注册实现类
 */
public class LoginServiceImpl implements LoginService {

	private UserDAO userdao;
	
	
	
	public UserDAO getUserdao() {
		return userdao;
	}
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	@Override
	public int register(User user) {
		//判断用户是否存在
				User u=userdao.findByName2(user.getName());
				if (u==null) {
					userdao.save(user);
					return 0;
				}
				//System.out.println(user.getName()+"该用户以存在");
				
				return 1;
	}
	@Override
	public int login(User user) {
		//判断用户是否存在
				User us=userdao.findByName2(user.getName());
				if (us==null) {
					//System.out.println("该用户不存在");
					return 1;
				}else{
					//判断密码是否正确
					if (us.getPassword().equals(user.getPassword())) {
						return 0;
					}else{
						//System.out.println("密码不正确");
						return 2;
					}
				}
	}
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return userdao.login(username, password);
	}
	
	

}
