package org.example.tasktrackerscheduler.kafka.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerscheduler.kafka.dto.EmailMessage;
import org.example.tasktrackerscheduler.kafka.dto.UserDailyReportResponse;
import org.example.tasktrackerscheduler.kafka.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.kafka.topic.email-sending}")
    private String topic;

    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;

    @Override
    public void sendDailyReports(List<UserDailyReportResponse> dailyReports) {

        for (UserDailyReportResponse report : dailyReports) {

            if(report.completedTasks().isEmpty() && report.pendingTasks().isEmpty()) {
                continue;
            }

            if (!report.pendingTasks().isEmpty() && report.completedTasks().isEmpty()) {
                
            } else if (report.pendingTasks().isEmpty() && !report.completedTasks().isEmpty()) {

            } else {

            }
        }
    }
}
