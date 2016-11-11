package com.jd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ������Ա���ݵ�������
 * @author bai 2016-10-31
 *
 */
//ָ����������ȼ������ж������ʱ������ԽС���ȼ�Խ��
@Order(1)
//�����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC�����У�������Ϊһ������
@Aspect
@Component
public class PeformAspect {

	//*-�������Ƶķ������ͣ�
	@Before("execution( * com.jd.aop.*.perform(..))")
	public void beforePerformance(){
		System.out.println("�������𣬵ƹ����𣬴�Ļ����");
	}
	
	@AfterReturning(value="execution( * com.jd.aop.*.perform(..))",returning="result")
	public void afterPerformanceReturn(JoinPoint joinPoint,Object result){
		System.out.println(result.toString());
		System.out.println("��Ա�³�����������");
	}
	
	@AfterThrowing(value="execution( * com.jd.aop.*.perform(..))",throwing="e")
	public void performException(JoinPoint joinPoint,Exception e){
		System.out.println(e.getMessage());
		System.out.println("��̨���Ա�����������ѡ���������");
	}
	
	@After("execution( * com.jd.aop.*.perform(..))")
	public void afterPerformance(){
		System.out.println("��Ļ����");
		System.out.println("ȫ����ԱлĻ��������");
	}
	
}
