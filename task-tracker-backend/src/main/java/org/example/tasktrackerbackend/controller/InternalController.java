package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.InternalApi;
import org.example.tasktrackerbackend.dto.response.UserDailyReportResponse;
import org.example.tasktrackerbackend.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InternalController implements InternalApi {

    private final ReportService reportService;

    @Override
    public ResponseEntity<List<UserDailyReportResponse>> getAllUsersReport() {
        List<UserDailyReportResponse> userReports = reportService.getAllUserReports();
        return ResponseEntity.status(200).body(userReports);
    }
}
