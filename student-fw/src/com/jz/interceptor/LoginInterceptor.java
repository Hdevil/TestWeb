package com.jz.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jz.bean.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义过滤器 进行登录验证
 * @author lenovo
 *
 */

public class LoginInterceptor extends AbstractInterceptor {

	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		//ActionContext ctx = arg0.getInvocationContext();  
//        Map session = ctx.getSession();  
//        User user = (User) session.get("user"); 

		HttpSession session=ServletActionContext.getRequest().getSession();
		 User user = (User) session.getAttribute("user"); 
        if (user != null) {  
            return arg0.invoke();  
        }  
		return "login";
	}

}
