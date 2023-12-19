package com.progresssoft.deals.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "public.fxdeal", groupId = "1")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}
