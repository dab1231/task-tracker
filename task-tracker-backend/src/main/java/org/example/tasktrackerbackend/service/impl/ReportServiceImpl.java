package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.dto.response.UserDailyReportResponse;
import org.example.tasktrackerbackend.entity.Task;
import org.example.tasktrackerbackend.enums.TaskStatus;
import org.example.tasktrackerbackend.repository.TaskRepository;
import org.example.tasktrackerbackend.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TaskRepository taskRepository;

    @Override
    public List<UserDailyReportResponse> getAllUserReports() {

        var uncompletedMap = taskRepository.findTasksByStatusNot(TaskStatus.DONE).
                stream().collect(Collectors.groupingBy(task -> task.getUser().getEmail()));
        var completedMap = taskRepository.
                findTasksByStatusAndCompletedAtAfter(TaskStatus.DONE, LocalDateTime.now().minusDays(1)).
                stream().collect(Collectors.groupingBy(task -> task.getUser().getEmail()));

        var uncompletedTasksGrouped = uncompletedMap.entrySet().
                stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().
                        stream().limit(5).map(Task::getTitle).toList()));
        var completedTasksGrouped = completedMap.entrySet().
                stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().
                        stream().limit(5).map(Task::getTitle).toList()));

        return Stream.concat(completedTasksGrouped.keySet().stream(), uncompletedTasksGrouped.keySet().stream()).
                distinct().map(email -> new UserDailyReportResponse
                        (email, uncompletedTasksGrouped.getOrDefault(email, List.of()),
                                completedTasksGrouped.getOrDefault(email, List.of()))).toList();
    }
}
