package com.jz.dao;

import com.jz.bean.Clasz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 	* A data access object (DAO) providing persistence and search support for Clasz entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.jz.bean.Clasz
  * @author MyEclipse Persistence Tools 
 */
@Transactional
public class ClaszDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ClaszDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String _TNAME = "TName";

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

    
    public void save(Clasz transientInstance) {
        log.debug("saving Clasz instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Clasz persistentInstance) {
        log.debug("deleting Clasz instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Clasz findById( java.lang.Integer id) {
        log.debug("getting Clasz instance with id: " + id);
        try {
            Clasz instance = (Clasz) getSession()
                    .get("com.jz.bean.Clasz", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Clasz> findByExample(Clasz instance) {
        log.debug("finding Clasz instance by example");
        try {
            List<Clasz> results = (List<Clasz>) getSession()
                    .createCriteria("com.jz.bean.Clasz")
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
      log.debug("finding Clasz instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Clasz as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Clasz> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Clasz> findByTName(Object TName
	) {
		return findByProperty(_TNAME, TName
		);
	}
	

	public List findAll() {
		log.debug("finding all Clasz instances");
		try {
			String queryString = "from Clasz";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Clasz merge(Clasz detachedInstance) {
        log.debug("merging Clasz instance");
        try {
            Clasz result = (Clasz) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Clasz instance) {
        log.debug("attaching dirty Clasz instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Clasz instance) {
        log.debug("attaching clean Clasz instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /**通过班级的id 删除班级
     * 
     * @param claszId 班级的id
     * @param isDelStud 是否可以关联删除学生 true-可以
     * @return 是否删除成功 true-成功
     */
    public boolean delete(int claszId,boolean isDelStud){
    	Clasz cls=this.findById(claszId);
    	if(!isDelStud&&cls.getStudents().size()>0)
    		return false;
    	
    	this.delete(cls);
    	return true;
    }
    
    /**
	 * 通过班级的名字查询班级（支持模糊查询）
	 * @param name 班级名字
	 * @return
	 */
	public List<Clasz> findByName(String name){
		String hql="from  Clasz s where s.name like ?";
    	Query qu=getSession().createQuery(hql);
    	qu.setString(0, "%"+name+"%");
    	
    	return qu.list();
		
	}
	
	/**
	 * 同步数据库
	 * @param cls
	 */
	public void reflush(Clasz cls){
		Session session=getSession();
		session.refresh(cls);
	}
	
	
    public static void main(String[] args) {
		ClaszDAO cladao=new ClaszDAO();
		Clasz cla=new Clasz();
//		cla.setName("san年三班");
//		cla.setTName("王老师");
//		cladao.save(cla);
		
		//
//		cla=cladao.findById(12);
//		Student s1=new Student();
//		s1.setBrith(new Date());
//		s1.setCode("142558");
//		s1.setName("xiaa");
//		s1.setSex(1);
//		s1.setClasz(cla);
//		cla.getStudents().add(s1);
//		cladao.attachDirty(cla);
		
//		cla=cladao.findById(12);
//		cladao.delete(cla);
		
//		System.out.println(cladao.delete(15, true));
		
		List<Clasz> l=new ArrayList<Clasz>();
		l=cladao.findByName("1");
		System.out.println(l);
	}
}