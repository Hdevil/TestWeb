package com.jz.action;


import com.jz.bean.Clasz;
import com.jz.bean.Student;
import com.jz.service.ClaszService;
import com.jz.service.ClaszServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ClaszOpAction extends ActionSupport {

	private ClaszService service;
	private Clasz clasz;
	
	public ClaszService getService() {
		return service;
	}

	public void setService(ClaszService service) {
		this.service = service;
	}
	//private Integer claId;
	
	
	

	public String add(){
		service.addClasz(clasz);
		
		return "success";
		
	}
	
	/**
	 * 删除班级访问方法
	 * @return
	 */
	public String del(){
		
		if(!clasz.equals("")){
			
		
		service.deleteClasz(clasz.getId(), true);
		}
		return "success";
		
	}
	
	/**
	 * 修改班级访问方法
	 * @return
	 */
	public String edit(){
		service.updateClasz(clasz);
		return "success";
		
	}
	
	public Clasz getClasz() {
		return clasz;
	}

	public void setClasz(Clasz clasz) {
		this.clasz = clasz;
	}



//	public Integer getClaId() {
//		return claId;
//	}
//
//	public void setClaId(Integer claszId) {
//		this.claId = claszId;
//	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
}
