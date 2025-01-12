package com.example.demo.entities;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Task {
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(Long id, String name, LocalDateTime creationDate, boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.completed = completed;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Task name connot be empty")
	private String name;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	private boolean completed;

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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
