package com.jd.h.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jd.h.bean.Master;
import com.jd.h.util.BaseHibernateDAO;

/**
 * 测试hibernate3种状态的转换
 * @author lenovo
 * 临时，持久，游离
 */
public class Testdao extends BaseHibernateDAO {

	/**
	 * 测试临时状态到持久状态的转变
	 */
	public void testT2P()
	{
		Session session=getSession();
		Transaction trans=session.beginTransaction();
		
		Master master =new Master();
		master.setName("小金");
		master.setSex(1);
		session.save(master);
		
		trans.commit();
		//持久态
		//master.setSex(0);
		session.close();
		//游离态
		//master.setName("aa");
		//临时态
		//master.setId(100);
	}
	/**
	 * 测试临时状态到持久状态后，更改属性提交。
	 */
	public void testT2P2Update()
	{
		Master master =new Master();
		master.setName("京巴");
		master.setSex(1);
		
		Session session=getSession();
		Transaction trans=session.beginTransaction();
		
		session.save(master);
		session.save(master);//只会保存一次
		master.setName("八斤");//session缓存区会自己对已有的对象比对更新
		trans.commit();
		session.close();
	}
	/**
	 * 测试持久状态修改后提交
	 */
	public void testP2Update()
	{
		Session session=getSession();
		Master master =(Master) session.get(Master.class, 3);
		Transaction trans=session.beginTransaction();
		//此处保存无意义
		session.save(master);
		
		master.setSex(1);
		master.setName("小八戒");
		trans.commit();
		session.close();
	}
	/**
	 * 将持久态转换为游离态后更新提交
	 * clear 或 evict 或 session关闭
	 */
	public void testP2D2Update()
	{
		Session session=getSession();
		//持久态
		Master master =(Master) session.get(Master.class, 3);
		//将持久对象转换游离对象
		session.evict(master);
		Transaction trans=session.beginTransaction();
		//游离态无法更新
		master.setName("xiao");
		trans.commit();
		session.close();
	}
	/**
	 * 将持久态转换为临时态
	 */
	public void testP2T()
	{
		Session session=getSession();
		//持久态
		Master master =(Master) session.get(Master.class, 3);
		
		Transaction trans=session.beginTransaction();
		//持久态转换临时态
		session.delete(master);
		//无法更新
		master.setName("aa");
		trans.commit();
		session.close();
		
	}
	/**
	 * 同步持久化对象
	 */
	public void testRefresh()
	{
		Session session=getSession();
		Master master =(Master) session.get(Master.class, 2);
		System.out.println("before"+master.getName());
		Transaction trans=session.beginTransaction();
		trans.commit();
		session.refresh(master);
		System.out.println("after"+master.getName());
		session.close();
	}
	/**
	 * 将游离态转换成持久态
	 */
	public void testD2P()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//临时态
		Master master=new Master();
		//游离态
		master.setId(4);
		master.setName("金币");
		master.setSex(0);
		session.update(master);
		//持久态
		String name=master.getName();
		trans.commit();
	}
	/**
	 * 持久状态修改id--报错
	 */
	public void testP2EditId()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		Master master =(Master) session.get(Master.class, 4);
		master.setId(100);
		trans.commit();
		session.close();
	}
	/**
	 * 游离态转换为暂时状态
	 */
	public void testD2S()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//临时态
		Master master=new Master();
		master.setId(4);
		//游离态
		session.delete(master);
		//临时态
		master.setSex(0);
		trans.commit();
		session.close();
		
	}
	/**
	 * 测试重复的持久化对象
	 */
	public void testDuplicateP()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//持久态
		Master master =(Master) session.get(Master.class, 2);
		//临时态
		Master master1 =new Master();
		//游离态
		master1.setId(2);
		master1.setName("金币");
		master1.setSex(0);
		session.merge(master1);
		//持久态
		System.out.println(master);
		System.out.println(master1);
		//master.setName("劲霸");
		master1.setName("京巴");
		trans.commit();
		
		session.close();
		
	}
	/**
	 * 消除重复的持久化对象
	 */
	public void testRemoveDupli()
	{
		
	}

	public static void main(String[] args) {
		Testdao tdao=new Testdao();
		//tdao.testT2P();//测试临时状态到持久状态的转变
		//tdao.testT2P2Update();//测试临时状态到持久状态后，更改属性提交。
		//tdao.testP2Update();//测试持久状态修改后提交
		//tdao.testP2D2Update();//将持久态转换为游离态后更新提交
		//tdao.testP2T();//将持久态转换为临时态
		//tdao.testRefresh();//同步持久化对象
		//tdao.testD2P();//将游离态转换成持久态
		//tdao.testP2EditId();//持久状态修改id--报错
		//tdao.testD2S();//游离态转换为暂时状态
		//tdao.testDuplicateP();//测试重复的持久化对象
	}
	
}
