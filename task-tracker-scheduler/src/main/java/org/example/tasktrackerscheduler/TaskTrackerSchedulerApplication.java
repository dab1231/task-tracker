package org.example.tasktrackerscheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskTrackerSchedulerApplication {

    @Bean
    public CommandLineRunner commandLineRunner(InternalApiClient internalApiClient) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println(internalApiClient.getAllUserReports("Me7qid03e8jpC6J+8ndLELPBaV2d4RiY9iE4stigT9UocJyRdMizhPzcyGgejLpC"));
            }
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerSchedulerApplication.class, args);
    }

}
