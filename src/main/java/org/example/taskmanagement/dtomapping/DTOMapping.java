package org.example.taskmanagement.dtomapping;

import org.example.taskmanagement.dto.TaskResponseDTO;
import org.example.taskmanagement.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class DTOMapping {
    public TaskResponseDTO mapToTaskResponseDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
