package com.jd.aop;

/**
 * ϲ����Ա
 * @author bai 2016-10-31
 *
 */
public class Funster implements Actor {

	@Override
	public String perform(String name, boolean isForgetWord)throws Exception {
		System.out.println(name+"˵�����˴����˴������ٸģ������ٷ���ǧ������");
		if(isForgetWord){
			throw new Exception("����̨����");
			
		}
		return name+"���ݽ���";
	}

}
