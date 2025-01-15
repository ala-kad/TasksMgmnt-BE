package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.demo.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findAll();
	List<Task> findByCompleted(Boolean completed);
	List<Task> findByCompletedOrderByCreationDateAsc(Boolean completed);
    List<Task> findByCompletedOrderByCreationDateDesc(Boolean completed);
    List<Task> findAllByOrderByCreationDateAsc();
    List<Task> findAllByOrderByCreationDateDesc(); 
}
