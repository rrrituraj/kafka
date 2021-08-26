package com.rituraj.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaConsumer {

    static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {"library-events"},groupId = "library-events-listener-group")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        log.info("Consumer Record : {} ", consumerRecord);
    }

}
