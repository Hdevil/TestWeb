package com.jz.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clasz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "clasz", catalog = "login")
public class Clasz implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String TName;
	private Set<Student> students = new HashSet<Student>(0);

	// Constructors

	/** default constructor */
	public Clasz() {
	}

	/** full constructor */
	public Clasz(String name, String TName, Set<Student> students) {
		this.name = name;
		this.TName = TName;
		this.students = students;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "t_name", length = 50)
	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clasz")
	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}