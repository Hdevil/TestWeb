package com.jz.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Black entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "black", catalog = "login")
public class Black implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Timestamp includeDate;
	private Integer removed;

	// Constructors

	/** default constructor */
	public Black() {
	}

	/** full constructor */
	public Black(String name, Timestamp includeDate, Integer removed) {
		this.name = name;
		this.includeDate = includeDate;
		this.removed = removed;
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

	@Column(name = "includeDate", length = 19)
	public Timestamp getIncludeDate() {
		return this.includeDate;
	}

	public void setIncludeDate(Timestamp includeDate) {
		this.includeDate = includeDate;
	}

	@Column(name = "removed")
	public Integer getRemoved() {
		return this.removed;
	}

	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

}