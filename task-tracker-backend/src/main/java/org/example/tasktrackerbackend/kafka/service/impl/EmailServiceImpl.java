package org.example.tasktrackerbackend.kafka.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.kafka.dto.EmailMessage;
import org.example.tasktrackerbackend.kafka.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.kafka.topic.email-sending}")
    private String topic;

    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;
    private final String TITLE = "Привет, спасибо за регистрацию в нашем приложении TASK TRACKER";
    private final String DESCRIPTION = """
            Мы очень благодарны вам, за использование нашего приложения.
            Будем рады обратной связи по поводу улучшения и недостатков.
            """;

    @Override
    public void sendWelcomeEmail(String recipientEmail) {
        EmailMessage message = new EmailMessage(recipientEmail, TITLE, DESCRIPTION);
        kafkaTemplate.send(topic, message);
    }
}
