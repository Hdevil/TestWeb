package com.jz.dao;

import com.jz.bean.Clasz;
import com.jz.bean.Student;
import com.jz.util.BaseHibernateDAO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 	* A data access object (DAO) providing persistence and search support for Student entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.jz.bean.Student
  * @author MyEclipse Persistence Tools 
 */
@Transactional
public class StudentDAO  {
	     private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String SEX = "sex";

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

    
    public void save(Student transientInstance) {
        log.debug("saving Student instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Student persistentInstance) {
        log.debug("deleting Student instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Student findById( java.lang.Integer id) {
        log.debug("getting Student instance with id: " + id);
        try {
            Student instance = (Student) getSession()
                    .get("com.jz.bean.Student", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Student> findByExample(Student instance) {
        log.debug("finding Student instance by example");
        try {
            List<Student> results = (List<Student>) getSession()
                    .createCriteria("com.jz.bean.Student")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Student instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Student as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Student> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Student> findByCode(Object code
	) {
		return findByProperty(CODE, code
		);
	}
	
	public List<Student> findBySex(Object sex
	) {
		return findByProperty(SEX, sex
		);
	}
	

	public List findAll() {
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Student merge(Student detachedInstance) {
        log.debug("merging Student instance");
        try {
            Student result = (Student) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Student instance) {
        log.debug("attaching dirty Student instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Student instance) {
        log.debug("attaching clean Student instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /**
     * 为某个班级添加多个学生
     * @param cla
     * @param stulist
     */
    public void save(Clasz cla,List<Student> stulist){
    	for(Student std:stulist){
    		std.setClasz(cla);
    		getSession().save(std);
    	}
    	
    }
    
    public void delete(int id){
    	String hql="delete from  Student user where user.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, id);
    	qu.executeUpdate();
    }
    
    /**
     * 删除某个班级的学生
     * @param claId
     */
    public void deleteByCla(int claId){
    	String hql="delete from  Student s where s.clasz.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, claId);
    	qu.executeUpdate();
    }
    
    public int deleteByClasName(String claszName){
    	String hql="from  Clasz s where s.name=?";
    	Query qu1=getSession().createQuery(hql);
    	qu1.setString(0, claszName);
    	Clasz cls=(Clasz) qu1.uniqueResult();
    	
    	 hql="delete from  Student s where s.clasz.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, cls.getId());
    	int a=qu.executeUpdate();
    	return a;
    	
    }
    
    /**将一个班级的学生更改到一个新的班级id
     * 
     * @param oldid
     * @param newid
     */
    public void changeCls(int oldid,int newid){
    	
    	String hql="from  Student s where s.clasz.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, oldid);
    	List<Student> slist=qu.list();
    	
    	Clasz newcls=(Clasz) getSession().get(Clasz.class, newid);
    	
    	for(Student stu:slist){
    		stu.setClasz(newcls);
    		getSession().save(stu);
    	}
    			
    	
    }
    
    /**
     * 根据名字查询学生（模糊查询）
     * @param name
     * @return
     */
    public List<Student> findByName2(String name){
    	String hql="from  Student s where s.name like ?";
    	Query qu=getSession().createQuery(hql);
    	qu.setString(0, "%"+name+"%");
    	
    	return qu.list();
    }
    
    /**
     * 根据学号查询学生
     * @param code
     * @return
     */
    public Student findByCode2(String code){
    	String hql="from  Student s where s.code = ?";
    	Query qu=getSession().createQuery(hql);
    	qu.setString(0, code);
    	
    	return (Student) qu.uniqueResult();
    }
    
    /**
     * 根据班级id查询所有学生
     * @param claszId
     * @return
     */
    public List<Student> findAllByclazId(int claszId){
    	String hql="from  Student s where s.clasz.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, claszId);
    	return qu.list();
    	
    }
    
    /**
     * 根据班级名字查询所有学生
     * @param name
     * @return
     */
    public List<Student> findAllByclazName(String name){
    	String hql="from  Student s where s.clasz.name=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setString(0, name);
    	return qu.list();
    }
    
    /**
     * 根据学生id查询班级信息
     * @param studId
     * @return
     */
    public Clasz findClaszByStudId(int studId){
    	Student s=(Student) getSession().get(Student.class, studId);
    	if(s!=null){
    		return s.getClasz();
    	}
    	return null;
    }
    
    /**
	 * 统计班级男女人数
	 * @param claszId
	 * @return 0-女 1-男
	 */
    public int[] countBySex(int claszId){
    	String hql="select  s.sex,count(s.id) from Student s where s.clasz.id=? group by s.sex";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, claszId);
    	List<Object[]> objlist=qu.list();
    	int[] nums=new int[2];
    	if(objlist!=null&&objlist.size()>0){
    		
    		for(Object[] obj:objlist){
    			int sex=(Integer) obj[0];
    			long count=(Long) obj[1];
    			if(sex==0){
    				nums[0]=(int) count;
    			}else{
    				nums[1]=(int) count;
    			}
    		}
    	}
    	return nums;
    }
    
    /**
     * 查询某个班级的学生的学号和姓名
     * @param clsId 班级id
     * @return key-学号 value-姓名
     */
    public Map<String, String> getStudentName(int clsId){
    	String hql="select  s.code,s.name from Student s where s.clasz.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, clsId);
    	
    	List<Object[]> objlist=qu.list();
    	Map<String, String> hmap=new HashMap<String, String>() ;
    	
    	for(Object[] obj:objlist){
    		String code=(String) obj[0];
    		String name=(String) obj[1];
    		hmap.put(code, name);
    	}
    	return hmap;
    }
    
    /**
	 * 通过名字查询（模糊查询）
	 * @param clsId 班级id
	 * @param name 查询名字
	 * @return 查询到的学生
	 */
    public List<Student> findByName(int clsId,String name){
    	
    	String hql="from  Student s where  s.clasz.id=? and s.name like ?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, clsId);
    	qu.setString(1, "%"+name+"%");
    	return qu.list();
    	
    }
    
    /**
	 * 通过分页查询某个班级的学生信息
	 * @param clsId 班级id
	 * @param startIndex起始数据的下标
	 * @param stopIndex结束数据的下标
	 * @return 查询的学生
	 */
    public List<Student> findByPage(int clsId,int startIndex,int stopIndex){
    	String hql="from  Student s where  s.clasz.id=? ";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, clsId);
    	qu.setFirstResult(startIndex);
    	qu.setMaxResults(stopIndex-startIndex+1);
    	
    	return qu.list();
    }
    
    public static void main(String[] args) {
		StudentDAO studao=new StudentDAO();
		Clasz cla=new Clasz();
		ClaszDAO cladao=new ClaszDAO();
		Student s1 =new Student();
//		cla=cladao.findById(11);
//		
//		Student s1 =new Student();
//		s1.setBrith(new Date());
//		s1.setCode("777778");
//		s1.setName("aa");
//		s1.setSex(1);
//		s1.setClasz(cla);
//		
//		Student s2 =new Student();
//		s2.setBrith(new Date());
//		s2.setCode("18888");
//		s2.setName("vv");
//		s2.setSex(1);
//		s2.setClasz(cla);
//		
//		List<Student> slist=new ArrayList<Student>();
//		slist.add(s1);
//		slist.add(s2);
//		studao.save(cla,slist);
		
		//studao.delete(58);
		//studao.deleteByCla(11);
		
		//System.out.println(studao.deleteByClasName("1"));
		//studao.changeCls(3, 11);
		
//		List<Student> l=new ArrayList<Student>();
//		l=studao.findByName2("小");
//		System.out.println(l);
		
//		s1=studao.findByCode2("175");
//		System.out.println(s1);
		
//		List<Student> l=new ArrayList<Student>();
//		l=studao.findAllByclazName("1");
//		System.out.println(l);
		
//		cla=studao.findClaszByStudId(3);
//		System.out.println(cla);
		
		System.out.println(Arrays.toString(studao.countBySex(2)));
		
//		Map<String, String> m=new HashMap<String, String>();
//		m=studao.getStudentName(11);
//		System.out.println(m);
		
//		List<Student> l=new ArrayList<Student>();
//		l=studao.findByName(2, "1");
//		System.out.println(l);
		
//		System.out.println(studao.findByPage(2, 1, 3));
	}
}