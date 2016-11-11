package com.jz.bean;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * Clasz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="clasz"
    ,catalog="login"
)

public class Clasz  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String t_name;
     private Set<Student> students = new LinkedHashSet<Student>(0);


    // Constructors

    /** default constructor */
    public Clasz() {
    }

    
    /** full constructor */
    public Clasz(String name, String TName, Set<Student> students) {
        this.name = name;
        this.t_name = TName;
        this.students = students;
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
    
    @Column(name="name", length=50)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="t_name", length=50)

    public String getT_name() {
        return this.t_name;
    }
    
    public void setT_name(String TName) {
        this.t_name = TName;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="clasz")
@OrderBy(" id ASC")
    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }


	
   








}