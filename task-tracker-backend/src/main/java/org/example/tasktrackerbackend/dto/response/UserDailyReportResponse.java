package org.example.tasktrackerbackend.dto.response;

import java.util.List;

public record UserDailyReportResponse(String email, List<String> pendingTasks, List<String> completedTasks) {
}
