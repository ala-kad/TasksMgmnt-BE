package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(Long id, String name, Date creationDate, boolean compeleted) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.compeleted = compeleted;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date creationDate;
	private boolean compeleted;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isCompeleted() {
		return compeleted;
	}
	public void setCompeleted(boolean compeleted) {
		this.compeleted = compeleted;
	}
}
