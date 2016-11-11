package com.jd.aop;

/**
 * 演员接口
 * @author bai 2016-10-31
 *
 */
public interface Actor {

	/**
	 * 表演
	 * @param name 演员的名字
	 * @param isForgetWord 是否忘记台词（true-忘记）
	 * @return 某个演员表演完毕
	 */
	String perform(String name,boolean isForgetWord)throws Exception;
	
}
