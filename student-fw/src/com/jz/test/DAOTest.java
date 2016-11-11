package com.jz.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jz.bean.Student;
import com.jz.bean.User;
import com.jz.dao.StudentDAO;
import com.jz.dao.UserDAO;

public class DAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new 
				ClassPathXmlApplicationContext("applicationContext.xml");
//		BlackDAO dao=(BlackDAO) ac.getBean("BlackDAO");
//		Black b=dao.findById(2);
//		System.out.println(b);
		
//		ClaszDAO dao=(ClaszDAO) ac.getBean("ClaszDAO");
//		Clasz c=dao.findById(2);
//		System.out.println(c);
		
//		StudentDAO dao=(StudentDAO) ac.getBean("StudentDAO");
//		Student s=dao.findById(2);
//		System.out.println(s);
		
		UserDAO dao=(UserDAO) ac.getBean("UserDAO");
		User user=dao.findById(9);
		System.out.println(user);
	}

}
