package com.jd.anno;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	
	public void doAdd(){
	 System.out.println("UserService.doAdd....");
	 userDao.doAdd();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
 
 
 
}
