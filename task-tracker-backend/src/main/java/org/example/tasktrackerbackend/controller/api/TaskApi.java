package org.example.tasktrackerbackend.controller.api;

import org.example.tasktrackerbackend.dto.request.TaskCreateRequest;
import org.example.tasktrackerbackend.dto.request.TaskUpdateRequest;
import org.example.tasktrackerbackend.dto.response.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

public interface TaskApi {

    @GetMapping("/tasks")
    ResponseEntity<List<TaskResponse>> getUserTasks(Principal principal);

    @PostMapping("/task")
    ResponseEntity<TaskResponse> createTask(@Validated @RequestBody TaskCreateRequest taskRequest, Principal principal);

    @PatchMapping("/task/{taskId}")
    ResponseEntity<TaskResponse> updateTask(@Validated @RequestBody TaskUpdateRequest taskRequest, @PathVariable Long taskId,Principal principal);

    @DeleteMapping("/task/{taskId}")
    ResponseEntity<Object> deleteTask(@PathVariable Long taskId, Principal principal);
}
