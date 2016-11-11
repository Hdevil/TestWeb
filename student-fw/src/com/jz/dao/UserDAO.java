package com.jz.dao;

import com.jz.bean.User;
import com.jz.util.BaseHibernateDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 	* A data access object (DAO) providing persistence and search support for User entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.jz.bean.User
  * @author MyEclipse Persistence Tools 
 */
@Transactional
public class UserDAO  {
	     private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";

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

    
    public void save(User transientInstance) {
        log.debug("saving User instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(User persistentInstance) {
        log.debug("deleting User instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public User findById( java.lang.Integer id) {
        log.debug("getting User instance with id: " + id);
        try {
            User instance = (User) getSession()
                    .get("com.jz.bean.User", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<User> findByExample(User instance) {
        log.debug("finding User instance by example");
        try {
            List<User> results = (List<User>) getSession()
                    .createCriteria("com.jz.bean.User")
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
      log.debug("finding User instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from User as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<User> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<User> findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	
	public List<User> findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List<User> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public User merge(User detachedInstance) {
        log.debug("merging User instance");
        try {
            User result = (User) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(User instance) {
        log.debug("attaching dirty User instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(User instance) {
        log.debug("attaching clean User instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /**
     * 根据id删除用户
     * @param id
     */
    public void delete(int id){
    	User user=this.findById(id);
    	this.delete(user);
    }
    
    /**
     * 通过hql语句删除用户
     * @param id
     */
    public void delete2(int id){
    	String hql="delete from User user where user.id=?";
    	Query qu=getSession().createQuery(hql);
    	qu.setInteger(0, id);
    	qu.executeUpdate();
    }
    
    /**
     * 通过sql语句删除用户
     * @param id
     */
    public void delete3(int id){
    	String sql="delete from user where id=:id";
    	SQLQuery qu=getSession().createSQLQuery(sql);
    	qu.setInteger("id", id);
    	qu.executeUpdate();
    }
    
    /**
     * 通过名字查询
     * @param name
     * @return
     */
    public User findByName2(String name){
    	String hql="from User user where user.name=?";
    	Session session=getSession();
    	Query qu=session.createQuery(hql);
    	qu.setString(0, name);
    	List<User> ulist=qu.list();
    	if(ulist==null||ulist.size()==0)
    		return null;
    	else
    	    return ulist.get(0);
    }
    
    public User login(String username, String password) {
//		Transaction trans=getSession().beginTransaction();
//		UserDAO userd=new UserDAO();
//		User user=new User();
//		String sql="select * from user where name=:username and password=:password";
//    	SQLQuery qu=getSession().createSQLQuery(sql);
//    	qu.setString("username", username);
//    	qu.setString("password", password);
//    	qu.executeUpdate();
//    	trans.commit();
//    	
//    	user=userd.findByName2(username);
    	return null;
	}
    
    public static void main(String[] args) {
		UserDAO udao=new UserDAO();
		//udao.delete(20);
		//udao.delete2(18);
		//udao.delete3(12);
		
		//System.out.println(udao.findByName2("6"));
		
		User u=new User();
		u.setEmail("ww");
		u.setName("aa");
		u.setPassword("aa");
		//u.setStatus(0);
		udao.attachDirty(u);
	}
}