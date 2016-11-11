package com.jd.aop;

/**
 * 喜剧演员
 * @author bai 2016-10-31
 *
 */
public class Funster implements Actor {

	@Override
	public String perform(String name, boolean isForgetWord)throws Exception {
		System.out.println(name+"说：犯了错，改了错，犯完再改，改完再犯，千锤百炼");
		if(isForgetWord){
			throw new Exception("忘记台词了");
			
		}
		return name+"表演结束";
	}

}
