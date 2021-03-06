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

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    /*@MockBean
    ListenableFuture<SendResult<String, Book>> resultListenableFuture;*/

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
        when(kafkaTemplate.send(isA(String.class), isA(Book.class))).thenReturn(null);

        //when
        mockMvc.perform(post("/publish")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
        //then
    }

    @Test
    void postKafkaMessage_is4xxError() throws Exception {
        Book book = new Book();
        book.setBookName(null);
        book.setAuthorName(null);


        String bookJson = objectMapper.writeValueAsString(book);
        when(kafkaTemplate.send(isA(String.class), isA(Book.class))).thenReturn(null);


        //when
        mockMvc.perform(post("/publish")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("authorName - must not be null , bookName - must not be null"));
        //then
    }
}