package com.jz.service;

import com.jz.bean.Clasz;





public interface ClaszService {
	//删除班级
	void deleteClasz(int clsId,boolean isDelStud);
	//更新班级
	void updateClasz(Clasz cls);
	//添加班级
	void addClasz(Clasz clasz);
}
