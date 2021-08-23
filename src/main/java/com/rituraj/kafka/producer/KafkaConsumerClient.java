package com.rituraj.kafka.producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerClient {

    @KafkaListener(topics = "TestTopic", groupId = "library-events-listener-group")
    public void consumes(String message) {
        System.out.println("message is:  " + message);
    }
}
