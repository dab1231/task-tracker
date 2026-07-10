package org.example.tasktrackerscheduler.kafka;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerscheduler.client.InternalApiClient;
import org.example.tasktrackerscheduler.kafka.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ReportScheduler {

    @Value("${internal.api.key}")
    private String apiKey;

    private final InternalApiClient internalApiClient;
    private final EmailService emailService;

    @Scheduled(cron = "${chron.time}")
    public void sendReports() {
        var allUserReports = internalApiClient.getAllUserReports(apiKey);
        emailService.sendDailyReports(allUserReports);
    }
}
