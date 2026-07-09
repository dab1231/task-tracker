package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.InternalApi;
import org.example.tasktrackerbackend.dto.response.UserDailyReportResponse;
import org.example.tasktrackerbackend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InternalController implements InternalApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<List<UserDailyReportResponse>> getAllUsersReport() {
        List<UserDailyReportResponse> userReports = taskService.getAllUserReports();
        return ResponseEntity.status(200).body(userReports);
    }
}
