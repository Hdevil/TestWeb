package com.jz.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.jz.bean.Black;
import com.jz.bean.User;
import com.jz.service.BlackServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class BlackAction extends ActionSupport implements ServletRequestAware{

	private BlackServiceImpl service;
	private HttpServletRequest request;
	private File img_file;
	
	
	public BlackServiceImpl getService() {
		return service;
	}

	public void setService(BlackServiceImpl service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {

		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null&&!user.getName().equals("admin")){
			
			return "mainAction";
		}
		
		request = ServletActionContext.getRequest();
		List<Black> list=service.getAll();
		request.setAttribute("black_list", list);
		
		
		return super.execute();
	}

	public String uploadImage(){
		
		//request = ServletActionContext.getRequest();
		String contextPathString=request.getSession().getServletContext().getRealPath("/");
		//创建文件对象
		File parentfile=new File(contextPathString+"/head_img","004.jpg");
		try {
			FileUtils.copyFile(img_file, parentfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}

	public File getImg_file() {
		return img_file;
	}

	public void setImg_file(File img_file) {
		this.img_file = img_file;
	}
	
	
}
