package com.jd.aop;

/**
 * ��Ա�ӿ�
 * @author bai 2016-10-31
 *
 */
public interface Actor {

	/**
	 * ����
	 * @param name ��Ա������
	 * @param isForgetWord �Ƿ�����̨�ʣ�true-���ǣ�
	 * @return ĳ����Ա�������
	 */
	String perform(String name,boolean isForgetWord)throws Exception;
	
}
