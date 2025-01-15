package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entities.Task;
import com.example.demo.repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * This class defines the RESTful API endpoints for managing tasks.
 * It handles CRUD operations (Create, Read, Update, Delete) on tasks.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
	@Autowired
	private TaskRepository taskRepo;
	
	/**
     * Retrieves all tasks, optionally filtered by completion status and sorted by creation date.
     *
     * @param completed (optional) Boolean parameter to filter tasks by completion status (true/false).
     * @param sortBy (optional) String parameter to specify the sorting field (e.g., "creationDate", "name").
     * @param sortOrder (optional) String parameter to specify the sort order ("asc" or "desc").
     * @return ResponseEntity containing a list of tasks or an empty response if no tasks are found.
     */
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(
	 @RequestParam(required = false) Boolean completed, 
	 @RequestParam(required = false) String sortBy, 
	 @RequestParam(required = false) String sortOrder
	 ){
		List<Task> tasks; 
		
		 if (completed != null) {
		        if ("asc".equalsIgnoreCase(sortOrder)) {
		            tasks = taskRepo.findByCompletedOrderByCreationDateAsc(completed);
		        } else if ("desc".equalsIgnoreCase(sortOrder)) {
		            tasks = taskRepo.findByCompletedOrderByCreationDateDesc(completed);
		        } else {
		            tasks = taskRepo.findByCompleted(completed); 
		        }
		    } else {
		        if ("asc".equalsIgnoreCase(sortOrder)) {
		            tasks = taskRepo.findAllByOrderByCreationDateAsc();
		        } else if ("desc".equalsIgnoreCase(sortOrder)) {
		            tasks = taskRepo.findAllByOrderByCreationDateDesc();
		        } else {
		            tasks = taskRepo.findAll();
		        }
		    }

		    if (tasks.isEmpty()) {
		        return ResponseEntity.noContent().build();
		    }
		    return ResponseEntity.ok(tasks);
		}
	
	 /**
     * Creates a new task.
     *
     * @param task The task object to be created.
     * @return ResponseEntity containing the created task object and a CREATED status code.
     */
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody @Valid Task task){
		task.setCreationDate(LocalDateTime.now());
		task.setCompleted(false);
		Task t = taskRepo.save(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(t);
	}
	
	/**
     * Updates an existing task.
     *
     * @param id The ID of the task to be updated.
     * @param task The updated task object.
     * @return ResponseEntity containing the updated task and an OK status code.
     * @throws ResponseStatusException if the task with the provided ID is not found.
     */
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
		Task t = taskRepo.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
		
		t.setName(task.getName());
		t.setCompleted(task.isCompleted());
		Task updatedTask = taskRepo.save(t);
		
		return ResponseEntity.ok(updatedTask);
	}
	
	/**
     * Deletes a task by ID.
     *
     * @param id The ID of the task to be deleted.
     * @return ResponseEntity with an OK status code if the task is deleted successfully,
     *         or a NOT_FOUND status code if the task is not found.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	    if (taskRepo.existsById(id)) {
	        taskRepo.deleteById(id);
	        return ResponseEntity.ok().build(); // Return 200 OK with no content
	    } else {
	        return ResponseEntity.notFound().build(); // Return 404 if the task doesn't exist
	    }
	}
	
	
}
