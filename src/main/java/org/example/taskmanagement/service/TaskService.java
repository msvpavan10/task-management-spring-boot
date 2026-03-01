package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.TaskRequestDTO;
import org.example.taskmanagement.dto.TaskResponseDTO;
import org.example.taskmanagement.dtomapping.DTOMapping;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final DTOMapping dtoMapping;

    public TaskService(TaskRepository taskRepository, DTOMapping dtoMapping) {
        this.taskRepository = taskRepository;
        this.dtoMapping = dtoMapping;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                    .stream()
                    .map(task -> dtoMapping.mapToTaskResponseDTO(task))
                    .toList();
    }

    public TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setCompleted(taskRequestDTO.isCompleted());
        task.setCreatedAt(LocalDate.now());

        Task savedTask = taskRepository.save(task);
        return dtoMapping.mapToTaskResponseDTO(savedTask);
    }

    public TaskResponseDTO getTaskById(long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return task == null ? null : dtoMapping.mapToTaskResponseDTO(task);
    }
}
