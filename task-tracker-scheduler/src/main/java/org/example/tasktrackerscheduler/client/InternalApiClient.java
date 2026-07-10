package org.example.tasktrackerscheduler.client;

import org.example.tasktrackerscheduler.kafka.dto.UserDailyReportResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface InternalApiClient {

    @GetExchange("/internal/reports")
    List<UserDailyReportResponse> getAllUserReports(@RequestHeader("X-Internal-Api-Key") String header);
}
