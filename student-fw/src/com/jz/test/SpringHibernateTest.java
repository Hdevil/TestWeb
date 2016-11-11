package com.jz.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jz.bean.User;

public class SpringHibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new 
				ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory factory=(SessionFactory)ac.getBean("sessionFactory");
		
		Session session=factory.openSession();
		User user=(User) session.get(User.class, 5);
		System.out.println(user);
	}

}
