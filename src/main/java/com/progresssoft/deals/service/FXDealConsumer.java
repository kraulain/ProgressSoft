package com.progresssoft.deals.service;

import com.progresssoft.deals.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FXDealConsumer {
    private static final Logger logger = LoggerFactory.getLogger(FXDealConsumer.class);

    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
    public void listen(String message) {
        logger.info("Received message: " + message);
        //marshall to fxdeal object
        //validate fxdeal
        //call mongodb persist
    }
}
