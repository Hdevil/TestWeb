package com.jz.interceptor;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jz.bean.Black;
import com.jz.bean.User;
import com.jz.service.BlackService;
import com.jz.service.BlackServiceImpl;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BlackInterceptor extends AbstractInterceptor {
	BlackService blacser;
	
	
	public BlackService getBlacser() {
		return blacser;
	}


	public void setBlacser(BlackService blacser) {
		this.blacser = blacser;
	}


	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		//HttpServletRequest request=ServletActionContext.getRequest();
		
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//		
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User) session.getAttribute("user");
		
		
		
//		if(user==null){
//			return arg0.invoke();
//		}
		List<Black> blaclist=blacser.getInTable();
		for(Black black:blaclist){
			if(user.getName().equals(black.getName())){
				return "error";
			}
		}
		
		return arg0.invoke();
	}

}
