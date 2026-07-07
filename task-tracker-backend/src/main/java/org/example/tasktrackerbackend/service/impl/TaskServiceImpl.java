package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.dto.request.TaskCreateRequest;
import org.example.tasktrackerbackend.dto.request.TaskUpdateRequest;
import org.example.tasktrackerbackend.dto.response.TaskResponse;
import org.example.tasktrackerbackend.entity.Task;
import org.example.tasktrackerbackend.enums.TaskStatus;
import org.example.tasktrackerbackend.exception.TaskNotFoundException;
import org.example.tasktrackerbackend.repository.TaskRepository;
import org.example.tasktrackerbackend.service.TaskService;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public List<TaskResponse> getUserTasks(Long userId) {

        var tasksByUserId = taskRepository.findTasksByUserId(userId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        for (Task task : tasksByUserId) {
            taskResponseList.add(new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCompletedAt()
            ));
        }
        return taskResponseList;
    }

    @Override
    public TaskResponse createTask(TaskCreateRequest task, Long userId) {
        var user = userService.findById(userId);
        var taskForDb = Task.builder().
                user(user)
                .title(task.title())
                .description(task.description())
                .status(TaskStatus.NOT_DONE)
                .build();

        var savedTask = taskRepository.save(taskForDb);
        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCompletedAt()
        );
    }

    @Override
    public TaskResponse updateTask(TaskUpdateRequest task, Long taskId, Long userId) {

        var taskFromDb = getTaskOrThrowEx(taskId, userId);
        if (!taskFromDb.getStatus().equals(TaskStatus.DONE) && task.status().equals(TaskStatus.DONE)) {
            taskFromDb.setCompletedAt(LocalDateTime.now());
        } else if (taskFromDb.getStatus().equals(TaskStatus.DONE) && !task.status().equals(TaskStatus.DONE)) {
            taskFromDb.setCompletedAt(null);
        }

        taskFromDb.setTitle(task.title());
        taskFromDb.setDescription(task.description());
        taskFromDb.setStatus(task.status());
        var savedTask = taskRepository.save(taskFromDb);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCompletedAt()
        );
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        var task = getTaskOrThrowEx(taskId, userId);
        taskRepository.delete(task);
    }

    private Task getTaskOrThrowEx(Long taskId, Long userId) {
        var maybeTask = taskRepository.findById(taskId);
        Task task;

        if (maybeTask.isPresent()) {
            task = maybeTask.get();
        } else {
            throw new TaskNotFoundException("Task not found");
        }
        if (!Objects.equals(task.getUser().getId(), userId)) {
            throw new TaskNotFoundException("Task not found");
        }
        return task;
    }
}
