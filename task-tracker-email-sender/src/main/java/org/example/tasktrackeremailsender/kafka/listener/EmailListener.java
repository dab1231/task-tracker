package org.example.tasktrackeremailsender.kafka.listener;

import org.example.tasktrackeremailsender.kafka.dto.EmailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {


    @KafkaListener(topics = "${spring.kafka.topic.email-sending}",
            containerFactory = "emailMessageConcurrentKafkaListenerContainerFactory")
    void listenerEmailMessage(EmailMessage emailMessage) {

    }
}
