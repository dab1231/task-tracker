package org.example.tasktrackerbackend.repository;

import org.example.tasktrackerbackend.entity.Task;
import org.example.tasktrackerbackend.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByUserId(Long userId);

    List<Task> findTasksByStatusAndCompletedAtAfter(TaskStatus status, LocalDateTime completedAtAfter);

    List<Task> findTasksByStatusNot(TaskStatus status);
}
