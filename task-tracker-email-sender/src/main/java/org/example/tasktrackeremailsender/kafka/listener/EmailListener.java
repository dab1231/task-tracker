package org.example.tasktrackeremailsender.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackeremailsender.kafka.dto.EmailMessage;
import org.example.tasktrackeremailsender.kafka.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.email-sending}",
            containerFactory = "emailMessageConcurrentKafkaListenerContainerFactory")
    void listenerEmailMessage(EmailMessage emailMessage) {

        emailService.sendEmail(emailMessage);
    }
}
