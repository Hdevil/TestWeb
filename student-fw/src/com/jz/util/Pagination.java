package com.jz.util;


/**
 * 分页数据处理工具类
 * @author qianlong 2016-9-9
 *
 */
public class Pagination {
private int count;//数据的总数
	
	private int pageSize;//每页的数据数
	
	private int currPage;//当前页

	public Pagination(int count, int pageSize) {
		super();
		this.count = count;
		this.pageSize = pageSize;
		//this.currPage = currPage;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount(){
		if(count<=pageSize)
			return 1;
		
		int size=count/pageSize;
		
		if(count%pageSize!=0)
			size++;
			return size;
	}
	
	/**
	 * 设置当前页
	 * @param page 当前页数
	 */
	public void setCurrPage(int page){
		if (page<=0) 
			this.currPage=1;
		
		if(page>this.getPageCount())
			this.currPage=this.getPageCount();
		else
			this.currPage=page;
	}
	
	/**
	 * 获取起始数据的下标
	 * @return
	 */
	public int getStartIndex(){
		return (currPage-1)*pageSize+1;
	}
	
	/**
	 * 获取数据的结束下标
	 * @return
	 */
	public int getStopIndex(){
		int cnt=currPage*pageSize;
		if(cnt>count)
			return count;
		else
		    return cnt;
	}
	
}
