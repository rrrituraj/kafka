package com.rituraj.kafka.producer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
public class KafkaProducerController {

    private final String TOPIC = "library-events";

    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public KafkaProducerController(KafkaTemplate<Integer, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static int getRandomInt() {
        Random ran = new Random();
        return ran.nextInt(10);
    }

    @PostMapping("/publish")
    public ResponseEntity<String> postKafkaMessage(@RequestBody final @Valid Book book) {
        int randomInt = getRandomInt();
        kafkaTemplate.send(TOPIC, randomInt, book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Published Successfully");
    }
}
