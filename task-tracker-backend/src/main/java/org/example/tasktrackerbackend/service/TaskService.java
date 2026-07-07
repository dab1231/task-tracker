package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.request.TaskCreateRequest;
import org.example.tasktrackerbackend.dto.request.TaskUpdateRequest;
import org.example.tasktrackerbackend.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getUserTasks(Long userId);

    TaskResponse createTask(TaskCreateRequest task, Long userId);

    TaskResponse updateTask(TaskUpdateRequest task, Long taskId, Long userId);

    void deleteTask(Long taskId, Long userId);
}
