package com.jz.service;


import com.jz.bean.Clasz;
import com.jz.dao.ClaszDAO;



public class ClaszServiceImpl implements ClaszService {

	private ClaszDAO clsDao;
	//private StudentDao studDao;

	public ClaszDAO getClsDao() {
		return clsDao;
	}

	public void setClsDao(ClaszDAO clsDao) {
		this.clsDao = clsDao;
	}
	
	@Override
	public void deleteClasz(int clsId,boolean isDelStud) {
		// TODO Auto-generated method stub
		clsDao.delete(clsId, isDelStud);
	}

	

	@Override
	public void updateClasz(Clasz cls) {
		clsDao.merge(cls);
		
	}

	@Override
	public void addClasz(Clasz clasz) {
		clsDao.save(clasz);
		
	}
	
	public static void main(String[] args) {
		ClaszServiceImpl cl=new ClaszServiceImpl();
//		Clasz cla=new Clasz();
//		cla.setName("qw");
//		cla.setT_name("qw");
		cl.deleteClasz(15, true);
	
	}
	
	

}
