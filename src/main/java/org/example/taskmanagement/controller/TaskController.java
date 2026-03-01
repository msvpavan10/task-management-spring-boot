package org.example.taskmanagement.controller;

import org.example.taskmanagement.dto.TaskRequestDTO;
import org.example.taskmanagement.dto.TaskResponseDTO;
import org.example.taskmanagement.exception.TaskNotFoundException;
import org.example.taskmanagement.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task-management")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    public TaskResponseDTO getTaskById(@PathVariable long id) {
         TaskResponseDTO taskResponseDTO = taskService.getTaskById(id);
         if(taskResponseDTO == null)
             throw new TaskNotFoundException("Task with id of " + id + " not found");
         return taskResponseDTO;
    }

    @GetMapping("/tasks")
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.addTask(taskRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskResponseDTO);
    }
}
