package com.jz.service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jz.bean.Clasz;
import com.jz.bean.Student;
import com.jz.dao.ClaszDAO;
import com.jz.dao.StudentDAO;
import com.jz.util.Pagination;



/**
 * 学生业务逻辑处理接口 实现类
 * @author qianlong 2016-9-5
 *
 */
public class StudentServiceImpl implements StudentService{

	public static final int PAGE_SIZE=5;
	private ClaszDAO clsDao;
	private StudentDAO studDao;
	
	
	
	public ClaszDAO getClsDao() {
		return clsDao;
	}
	public void setClsDao(ClaszDAO clsDao) {
		this.clsDao = clsDao;
	}
	public StudentDAO getStudDao() {
		return studDao;
	}
	public void setStudDao(StudentDAO studDao) {
		this.studDao = studDao;
	}
	@Override
	public Map<Integer, String> getClaszNameWithId() {
		Map<Integer, String> map=new LinkedHashMap<Integer, String>();
		//获取所有班级的数据
		List<Clasz> sList=clsDao.findAll();
		//将每个班级的id和name存入到map中
		for (Clasz cls : sList) {
			map.put(cls.getId(), cls.getName());
		}
		return map;
	}
	@Override
	public int[] countBySex(int claszId) {
		// TODO Auto-generated method stub
		return studDao.countBySex(claszId);
	}
	@Override
	public Clasz getClasz(int claszId) {
		Clasz cls=clsDao.findById(claszId);
		//clsDao.reflush(cls);
		return cls;
	}
	@Override
	public void add(Student s) {
		// TODO Auto-generated method stub
		studDao.save(s);
	}
	@Override
	public void deleteStudent(int studId) {
		// TODO Auto-generated method stub
		studDao.delete(studId);
	}
	@Override
	public void updateStudent(Student stud) {
		// TODO Auto-generated method stub
		studDao.merge(stud);
	}
	@Override
	public List<Student> findByName(String name) {
		// TODO Auto-generated method stub
		return studDao.findByName2(name);
	}
	@Override
	public List<Student> findByName(int clsid, String name) {
		// TODO Auto-generated method stub
		return studDao.findByName(clsid, name);
	}
	@Override
	public List<Student> findByPage(int clsId, int count, int currPage) {
		Pagination pagination =new Pagination(count, PAGE_SIZE);
		pagination.setCurrPage(currPage);
		int startIndex=pagination.getStartIndex();
		int stopIndex=pagination.getStopIndex();
		return studDao.findByPage(clsId, startIndex, stopIndex);
	}
	
	public static void main(String[] args) {
		StudentServiceImpl ss=new StudentServiceImpl();
		Clasz cl=new Clasz();
		cl.setId(2);
		Student stu=new Student();
		stu.setId(6);
		stu.setName("xiao");
		ss.updateStudent(stu);
	    System.out.println("---");
	    
	    
	}
	

	
}
