package org.example.tasktrackerscheduler.kafka.service;

import org.example.tasktrackerscheduler.kafka.dto.UserDailyReportResponse;

import java.util.List;

public interface EmailService {

    void sendDailyReports(List<UserDailyReportResponse> dailyReports);
}
