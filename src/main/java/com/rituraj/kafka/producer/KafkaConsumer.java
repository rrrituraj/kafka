package com.rituraj.kafka.producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "TestTopic", groupId = "group_id")
    public void consumes(String message) {
        System.out.println("message is:  " + message);
    }
}
