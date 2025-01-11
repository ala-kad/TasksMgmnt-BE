package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Task;
import com.example.demo.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class TaskController {
	@Autowired
	private TaskRepository taskRepo;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks(){
		List<Task>  tasks = taskRepo.findAll();
		return ResponseEntity.ok(tasks);
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@RequestBody Task task){
		Task t = taskRepo.save(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(t);
	}
	
	@PutMapping("tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, Task task){
		Task t = taskRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
		
		t.setName(task.getName());
		t.setCreationDate(task.getCreationDate());
		t.setCompeleted(task.isCompeleted());
		Task updatedTask = taskRepo.save(t);
		
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	    if (taskRepo.existsById(id)) {
	        taskRepo.deleteById(id);
	        return ResponseEntity.ok().build(); // Return 200 OK with no content
	    } else {
	        return ResponseEntity.notFound().build(); // Return 404 if the task doesn't exist
	    }
	}
	
	
}
