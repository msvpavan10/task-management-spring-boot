package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.TaskResponseDTO;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                    .stream()
                    .map(task -> TaskResponseDTO.builder()
                            .id(task.getId())
                            .title(task.getTitle())
                            .description(task.getDescription())
                            .completed(task.isCompleted())
                            .createdAt(task.getCreatedAt())
                            .build())
                    .toList();
    }
}
