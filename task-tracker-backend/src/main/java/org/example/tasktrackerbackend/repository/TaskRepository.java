package org.example.tasktrackerbackend.repository;

import org.example.tasktrackerbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByUserId(Long userId);

}
