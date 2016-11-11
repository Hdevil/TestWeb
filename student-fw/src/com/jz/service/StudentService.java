package com.jz.service;


import java.util.List;
import java.util.Map;

import com.jz.bean.Clasz;
import com.jz.bean.Student;





/**
 * 学生业务逻辑处理接口
 * @author qianlong 2016-9-5
 *
 */

public interface StudentService {
	/**
	 * 获取所有的班级名称和id
	 * @return
	 */
	Map<Integer,String> getClaszNameWithId();
	
	/**
	 * 统计某个班级的男女人数
	 * @param classzId 班级的id
	 * @return 数组下标0-女生人数 1-男生人数
	 */
	int[] countBySex(int claszId);
	
	/**
	 *  通过班级的id获取某个班级对象
	 * @param classzId 班级的id
	 * @return 班级对象
	 */
	Clasz getClasz(int claszId);
	
	/**
	 * 添加学生
	 * @param s
	 */
	void add(Student s);
	
	/**
	 * 删除学生
	 * @param studId学生的id
	 */
	void deleteStudent(int studId);
	
	/**
	 * 修改学生信息
	 * @param stud
	 */
	void updateStudent(Student stud);
	
	/**
	 * 通过名字查询
	 * @param name 学生模糊名字
	 * @return 查询到的学生
	 */
	List<Student> findByName(String name);
	
	/**
	 * 通过名字查询某个班的学生
	 * @param clsid 班级id
	 * @param name 学生模糊名字
	 * @return
	 */
	List<Student> findByName(int clsid,String name);
	
	/**获取当前页的学生信息
	 * clsid 班级id
	 * count 总记录数
	 * @param currPage当前的分页
	 * @return
	 */
	List<Student> findByPage(int clsId,int count,int currPage);
}