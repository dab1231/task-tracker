package org.example.tasktrackerscheduler.kafka.dto;

import java.util.List;

public record UserDailyReportResponse(String email, List<String> pendingTasks, List<String> completedTasks) {
}
