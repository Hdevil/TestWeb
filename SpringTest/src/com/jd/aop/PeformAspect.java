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
 * 话剧演员表演的切面类
 * @author bai 2016-10-31
 *
 */
//指定切面的优先级，当有多个切面时，数字越小优先级越高
@Order(1)
//把这个类声明为一个切面：需要把该类放入到IOC容器中，在声明为一个切面
@Aspect
@Component
public class PeformAspect {

	//*-代表不限制的返回类型，
	@Before("execution( * com.jd.aop.*.perform(..))")
	public void beforePerformance(){
		System.out.println("音乐响起，灯光亮起，大幕拉开");
	}
	
	@AfterReturning(value="execution( * com.jd.aop.*.perform(..))",returning="result")
	public void afterPerformanceReturn(JoinPoint joinPoint,Object result){
		System.out.println(result.toString());
		System.out.println("演员下场。。。。。");
	}
	
	@AfterThrowing(value="execution( * com.jd.aop.*.perform(..))",throwing="e")
	public void performException(JoinPoint joinPoint,Exception e){
		System.out.println(e.getMessage());
		System.out.println("后台题词员进行忘词提醒。。。。。");
	}
	
	@After("execution( * com.jd.aop.*.perform(..))")
	public void afterPerformance(){
		System.out.println("大幕降下");
		System.out.println("全体演员谢幕。。。。");
	}
	
}
