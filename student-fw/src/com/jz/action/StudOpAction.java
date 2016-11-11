package com.jz.action;

import java.util.Iterator;

import com.jz.bean.Clasz;
import com.jz.bean.Student;
import com.jz.service.StudentService;
import com.jz.service.StudentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class StudOpAction extends ActionSupport {

	private StudentService service;
	
	private Student student;
	
	private Integer claszId;
	
	
	public StudentService getService() {
		return service;
	}

	public void setService(StudentService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 添加学生访问方法
	 * @return
	 */
	public String add(){
		Clasz clasz=new Clasz();
		clasz.setId(claszId);
		student.setClasz(clasz);
		service.add(student);
		return "input";
		
	}
	
	/**
	 * 删除学生访问方法
	 * @return
	 */
	public String del(){
		
		Clasz cls=service.getClasz(claszId);
		Iterator<Student> it=cls.getStudents().iterator();
		while (it.hasNext()) {
			Student s =  it.next();
			if (s.getId().equals(student.getId())) {
				it.remove();
				service.deleteStudent(s.getId());
			}
		}
		return "success";
		
	}
	
	/**
	 * 修改学生访问方法
	 * @return
	 */
	public String edit(){
		Clasz cls=new Clasz();
		cls.setId(claszId);
		student.setClasz(cls);
		service.updateStudent(student);
		return "success";
		
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getClaszId() {
		return claszId;
	}

	public void setClaszId(Integer claszId) {
		this.claszId = claszId;
	}
	
	
}
