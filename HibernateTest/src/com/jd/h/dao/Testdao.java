package com.jd.h.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jd.h.bean.Master;
import com.jd.h.util.BaseHibernateDAO;

/**
 * ����hibernate3��״̬��ת��
 * @author lenovo
 * ��ʱ���־ã�����
 */
public class Testdao extends BaseHibernateDAO {

	/**
	 * ������ʱ״̬���־�״̬��ת��
	 */
	public void testT2P()
	{
		Session session=getSession();
		Transaction trans=session.beginTransaction();
		
		Master master =new Master();
		master.setName("С��");
		master.setSex(1);
		session.save(master);
		
		trans.commit();
		//�־�̬
		//master.setSex(0);
		session.close();
		//����̬
		//master.setName("aa");
		//��ʱ̬
		//master.setId(100);
	}
	/**
	 * ������ʱ״̬���־�״̬�󣬸��������ύ��
	 */
	public void testT2P2Update()
	{
		Master master =new Master();
		master.setName("����");
		master.setSex(1);
		
		Session session=getSession();
		Transaction trans=session.beginTransaction();
		
		session.save(master);
		session.save(master);//ֻ�ᱣ��һ��
		master.setName("�˽�");//session���������Լ������еĶ���ȶԸ���
		trans.commit();
		session.close();
	}
	/**
	 * ���Գ־�״̬�޸ĺ��ύ
	 */
	public void testP2Update()
	{
		Session session=getSession();
		Master master =(Master) session.get(Master.class, 3);
		Transaction trans=session.beginTransaction();
		//�˴�����������
		session.save(master);
		
		master.setSex(1);
		master.setName("С�˽�");
		trans.commit();
		session.close();
	}
	/**
	 * ���־�̬ת��Ϊ����̬������ύ
	 * clear �� evict �� session�ر�
	 */
	public void testP2D2Update()
	{
		Session session=getSession();
		//�־�̬
		Master master =(Master) session.get(Master.class, 3);
		//���־ö���ת���������
		session.evict(master);
		Transaction trans=session.beginTransaction();
		//����̬�޷�����
		master.setName("xiao");
		trans.commit();
		session.close();
	}
	/**
	 * ���־�̬ת��Ϊ��ʱ̬
	 */
	public void testP2T()
	{
		Session session=getSession();
		//�־�̬
		Master master =(Master) session.get(Master.class, 3);
		
		Transaction trans=session.beginTransaction();
		//�־�̬ת����ʱ̬
		session.delete(master);
		//�޷�����
		master.setName("aa");
		trans.commit();
		session.close();
		
	}
	/**
	 * ͬ���־û�����
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
	 * ������̬ת���ɳ־�̬
	 */
	public void testD2P()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//��ʱ̬
		Master master=new Master();
		//����̬
		master.setId(4);
		master.setName("���");
		master.setSex(0);
		session.update(master);
		//�־�̬
		String name=master.getName();
		trans.commit();
	}
	/**
	 * �־�״̬�޸�id--����
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
	 * ����̬ת��Ϊ��ʱ״̬
	 */
	public void testD2S()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//��ʱ̬
		Master master=new Master();
		master.setId(4);
		//����̬
		session.delete(master);
		//��ʱ̬
		master.setSex(0);
		trans.commit();
		session.close();
		
	}
	/**
	 * �����ظ��ĳ־û�����
	 */
	public void testDuplicateP()
	{
		Session session=getSession();
		
		Transaction trans=session.beginTransaction();
		//�־�̬
		Master master =(Master) session.get(Master.class, 2);
		//��ʱ̬
		Master master1 =new Master();
		//����̬
		master1.setId(2);
		master1.setName("���");
		master1.setSex(0);
		session.merge(master1);
		//�־�̬
		System.out.println(master);
		System.out.println(master1);
		//master.setName("����");
		master1.setName("����");
		trans.commit();
		
		session.close();
		
	}
	/**
	 * �����ظ��ĳ־û�����
	 */
	public void testRemoveDupli()
	{
		
	}

	public static void main(String[] args) {
		Testdao tdao=new Testdao();
		//tdao.testT2P();//������ʱ״̬���־�״̬��ת��
		//tdao.testT2P2Update();//������ʱ״̬���־�״̬�󣬸��������ύ��
		//tdao.testP2Update();//���Գ־�״̬�޸ĺ��ύ
		//tdao.testP2D2Update();//���־�̬ת��Ϊ����̬������ύ
		//tdao.testP2T();//���־�̬ת��Ϊ��ʱ̬
		//tdao.testRefresh();//ͬ���־û�����
		//tdao.testD2P();//������̬ת���ɳ־�̬
		//tdao.testP2EditId();//�־�״̬�޸�id--����
		//tdao.testD2S();//����̬ת��Ϊ��ʱ״̬
		//tdao.testDuplicateP();//�����ظ��ĳ־û�����
	}
	
}
