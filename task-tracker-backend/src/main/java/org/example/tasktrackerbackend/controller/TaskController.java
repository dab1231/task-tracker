package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.TaskApi;
import org.example.tasktrackerbackend.dto.request.TaskCreateRequest;
import org.example.tasktrackerbackend.dto.request.TaskUpdateRequest;
import org.example.tasktrackerbackend.dto.response.TaskResponse;
import org.example.tasktrackerbackend.service.TaskService;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;
    private final UserService userService;

    @Override
    public ResponseEntity<List<TaskResponse>> getUserTasks(Principal principal) {
        var userId = userService.getIdByEmail(principal.getName());
        var userTasks = taskService.getUserTasks(userId);
        return ResponseEntity
                .status(200)
                .body(userTasks);
    }

    @Override
    public ResponseEntity<TaskResponse> createTask(TaskCreateRequest taskRequest, Principal principal) {
        var userId = userService.getIdByEmail(principal.getName());
        var task = taskService.createTask(taskRequest, userId);
        return ResponseEntity
                .status(201)
                .body(task);
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(TaskUpdateRequest taskRequest, Long taskId, Principal principal) {
        var userId = userService.getIdByEmail(principal.getName());
        var taskResponse = taskService.updateTask(taskRequest, taskId, userId);
        return ResponseEntity
                .status(200)
                .body(taskResponse);
    }

    @Override
    public ResponseEntity<Object> deleteTask(Long taskId, Principal principal) {
        var userId = userService.getIdByEmail(principal.getName());
        taskService.deleteTask(taskId, userId);
        return ResponseEntity
                .status(204)
                .build();
    }
}
