package com.rituraj.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, Book> kafkaTemplate;
    private final String TOPIC = "TestTopic";
    @PostMapping("/publish")
    public String postKafkaMessage(@RequestBody final Book book){
        kafkaTemplate.send(TOPIC, book);
        return "published successfully";
    }
}
