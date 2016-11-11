package com.jz.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jz.bean.Student;
import com.jz.bean.User;
import com.jz.service.LoginService;
import com.jz.service.StudentService;

public class ServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new 
				ClassPathXmlApplicationContext("applicationContext.xml");
//		BlackService b=(BlackService) ac.getBean("BlackService");
//		Black bl=b.findByName("bb");
//		System.out.println(bl);
		
		//LoginService l=(LoginService) ac.getBean("LoginService");
		StudentService s=(StudentService) ac.getBean("StudentService");
		List<Student> st=s.findByName(2, "小");
		System.out.println(st);
		
	}

}
