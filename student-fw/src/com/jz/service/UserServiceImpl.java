package com.jz.service;


import java.sql.Connection;
import java.util.List;

import com.jz.bean.User;
import com.jz.dao.UserDAO;




public class UserServiceImpl implements UserService {

	private UserDAO userdao;

	
	
	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return userdao.findAll();
	}

	@Override
	public List findBlacklist() {
		// TODO Auto-generated method stub
		return userdao.findByStatus(1);
		 
	}

	@Override
	public void updateStatus(User user) {
		userdao.attachDirty(user);
		
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userdao.findById(id);
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return userdao.findByName2(name);
	}

	@Override
	public void updateUser(User user) {
		userdao.attachDirty(user);
		
	}

	@Override
	public void addUser(User user) {
		userdao.save(user);
		
	}

	@Override
	public void deleteUser(int id) {
		userdao.delete(id);
		
	}
	
	public static void main(String[] args) {
		UserServiceImpl us=new UserServiceImpl();
		List list=us.findAll();
	    System.out.println(list);
	}
}
