package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.response.UserDailyReportResponse;

import java.util.List;

public interface ReportService {

    List<UserDailyReportResponse> getAllUserReports();
}
