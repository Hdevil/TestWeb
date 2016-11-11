package com.jz.action;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jz.bean.Clasz;
import com.jz.bean.Student;
import com.jz.service.StudentService;
import com.jz.service.StudentServiceImpl;
import com.jz.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {

	private StudentService service;
	private Integer clsId;
	private HttpServletRequest request;
	private Integer pageNum;
	private String findname;
	
	private Map<Integer, String> nameMap;
	
	private int[] count;
	private Integer pageCount;
	
	
	
	public StudentService getService() {
		return service;
	}

	public void setService(StudentService service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		
		request = ServletActionContext.getRequest();
		//获取班级的id和名字
		 nameMap=service.getClaszNameWithId();
		if(clsId==null){
			for (Entry<Integer,String> entry:nameMap.entrySet()) {
				clsId=entry.getKey();
				break;
			}
		}
		//获取男女人数
		 count=service.countBySex(clsId);
		 if(!count.equals("")){
		 int n=count[0]+count[1];
		 System.out.println("--------------"+n+"------------");
		 }
		
		 int pagesize=StudentServiceImpl.PAGE_SIZE;
			if(pageNum==null){
				pageNum=1;
			}
		 
		 //获取班级信息
		Clasz cls=service.getClasz(clsId);
		
		if(!cls.equals("")){
		int studcount=cls.getStudents().size();
		
		
		//进行分页
		if(studcount>pagesize){
//			Set<Student> slist=(Set<Student>) service.findByPage(clsId, studcount, pageNum);
//			cls.setStudents(slist);
			
			List<Student> slist= service.findByPage(clsId, studcount, pageNum);
			cls.setStudents(new LinkedHashSet<Student>(slist));
		}
		//进行查询
		if (findname!=null&&!findname.equals("")) {
			//findname=new String(findname.getBytes("iso-8859-1"),"utf-8");
			List<Student> studList=service.findByName(clsId, findname);
			cls.setStudents(new LinkedHashSet<Student>(studList));
		}
		//request.setAttribute("nameMap", nameMap);
		//request.setAttribute("count", count);
		request.setAttribute("cls", cls);
		Pagination page=new Pagination(studcount,pagesize);
		pageCount=page.getPageCount();
		}
		//request.setAttribute("pageCount", page.getPageCount());
		//request.setAttribute("pageNum", pageNum);
		
		return super.execute();
	}
	
	public Integer getClsId() {
		return clsId;
	}


	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getFindname() {
		return findname;
	}

	public void setFindname(String findname) {
		this.findname = findname;
	}

	public Map<Integer, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<Integer, String> nameMap) {
		this.nameMap = nameMap;
	}

	

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public int[] getCount() {
		return count;
	}

	public void setCount(int[] count) {
		this.count = count;
	}

	
	

	
	
}
