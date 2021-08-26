package com.rituraj.kafka.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"TestTopic","library-events"}, partitions = 3, ports = {9092})
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap-servers=${spring.embedded.kafka.brokers}"})
class KafkaProducerControllerTest1 {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void postProducerKafkaMessage() {
        Book book = new Book();
//        book.setBookId(1);
        book.setBookName("kafka-Learn");
        book.setAuthorName("Rituraj");

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);

        //when
        ResponseEntity<Book> responseEntity = restTemplate.exchange("/publish", HttpMethod.POST, httpEntity, Book.class);

        //then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }
}