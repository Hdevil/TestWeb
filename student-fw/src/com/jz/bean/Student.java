package com.jz.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.jz.util.DateUtil;


/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="student"
    ,catalog="login"
)

public class Student  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Clasz clasz;
     private String name;
     private String code;
     private Integer sex;
     private Date brith;


    // Constructors

    /** default constructor */
    public Student() {
    }

    
    /** full constructor */
    public Student(Clasz clasz, String name, String code, Integer sex, Date brith) {
        this.clasz = clasz;
        this.name = name;
        this.code = code;
        this.sex = sex;
        this.brith = brith;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="class_id")

    public Clasz getClasz() {
        return this.clasz;
    }
    
    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }
    
    @Column(name="name", length=50)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="code", length=50)

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="sex")

    public Integer getSex() {
        return this.sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="brith", length=10)

    public Date getBrith() {
        return this.brith;
    }
    
    public void setBrith(Date brith) {
        this.brith = brith;
    }

    @Transient
    public String getFormatBirth(){
    	if(brith!=null)
    		return DateUtil.toString(brith, "yyyy年MM月dd日");
    	return "";
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", clasz=" + clasz + ", name=" + name
				+ ", code=" + code + ", sex=" + sex + ", brith=" + brith + "]";
	}


	
   








}