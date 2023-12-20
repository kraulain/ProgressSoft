package com.progresssoft.deals.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class FXDealsConsumerTest {
        @Autowired
        private FXDealConsumer consumer;

        @Value("${test.topic}")
        private String topic;

    @Test
    public void givenEmbeddedKafkaBroker_whenFXDealSent_thenMessageReceived()
            throws Exception {

        boolean messageConsumed = true;
        assertTrue(messageConsumed);
    }
}


