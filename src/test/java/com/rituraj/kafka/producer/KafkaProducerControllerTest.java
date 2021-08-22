package com.rituraj.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KafkaProducerController.class)
@AutoConfigureMockMvc
class KafkaProducerControllerTest {

    private final String TOPIC = "TestTopic";
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    KafkaTemplate<String, Book> kafkaTemplate;
    @MockBean
    ListenableFuture<SendResult<String,Book>> resultListenableFuture;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void postKafkaMessage() throws Exception {
        Book book = new Book();
        book.setBookName("Kafka");
        book.setAuthorName("rituraj");


        String bookJson = objectMapper.writeValueAsString(book);
        doReturn(resultListenableFuture).when(kafkaTemplate).send(TOPIC, book);

        //when
        mockMvc.perform(post("/publish")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
        //then
    }
}