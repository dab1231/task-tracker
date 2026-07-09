package org.example.tasktrackerbackend.controller.api;

import org.example.tasktrackerbackend.dto.response.UserDailyReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/internal")
public interface InternalApi {

    @GetMapping("/reports")
    ResponseEntity<List<UserDailyReportResponse>> getAllUsersReport();
}
