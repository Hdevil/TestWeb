package com.jz.service;

/*
 *用户信息操作
 */

import java.util.List;

import com.jz.bean.User;





public interface UserService {
    //查询全部用户
	List findAll();
	//查询在黑名单里的用户
	List findBlacklist();
	//更新用户的黑名单状态
	void updateStatus(User user);
	//根据id查询用户
	User findById(int id);
	//根据用户名查询
	User findByName(String name);
	//更新用户信息
	void updateUser(User user);
	//添加新用户
	void addUser(User user);
	//删除用户
	void deleteUser(int id);
}
