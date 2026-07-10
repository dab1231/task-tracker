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

            if (report.completedTasks().isEmpty() && report.pendingTasks().isEmpty()) {
                continue;
            }

            EmailMessage emailMessage;

            if (!report.pendingTasks().isEmpty() && report.completedTasks().isEmpty()) {

                var description = String.join(" ,", report.pendingTasks());
                emailMessage = new EmailMessage(
                        report.email(),
                        "У вас осталось " + report.pendingCount() + " несделанных задач",
                        description
                );
            } else if (report.pendingTasks().isEmpty()) {

                var description = String.join(" ,", report.completedTasks());
                emailMessage = new EmailMessage(
                        report.email(),
                        "За сегодня вы выполнили " + report.completedCount() + " задач",
                        description
                );
            } else {

                var descriptionPending = String.join(" ,", report.pendingTasks());
                descriptionPending = "Невыполненные задачи: " + descriptionPending + "\n";
                var descriptionCompleted = String.join(" ,", report.completedTasks());
                descriptionCompleted = "Выполненные задачи: " + descriptionCompleted + "\n";

                var finalDescription = descriptionPending.concat(descriptionCompleted);
                emailMessage = new EmailMessage(
                        report.email(),
                        "Количество невыполненных задач: " + report.pendingCount() +
                                "\n Количество выполненных задач: " + report.completedCount(),
                        finalDescription
                );
            }

            kafkaTemplate.send(topic, emailMessage);
        }
    }
}
