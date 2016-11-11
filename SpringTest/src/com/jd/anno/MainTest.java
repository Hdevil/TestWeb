package com.jd.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainTest {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction a=(UserAction) ac.getBean("userAction");
		a.execute();
	}

}
