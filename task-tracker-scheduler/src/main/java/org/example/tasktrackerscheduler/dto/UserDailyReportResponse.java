package org.example.tasktrackerscheduler.dto;

import java.util.List;

public record UserDailyReportResponse(String email, List<String> pendingTasks, List<String> completedTasks) {
}
