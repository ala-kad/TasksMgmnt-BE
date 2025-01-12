package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.demo.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findAll(); 
}
