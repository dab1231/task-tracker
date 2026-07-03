package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.request.TaskCreateRequest;
import org.example.tasktrackerbackend.dto.request.TaskUpdateRequest;
import org.example.tasktrackerbackend.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getUserTasks(Long userId);

    Task createTask(TaskCreateRequest task, Long userId);

    Task updateTask(TaskUpdateRequest task, Long taskId, Long userId);

    void deleteTask(Long taskId, Long userId);
}
