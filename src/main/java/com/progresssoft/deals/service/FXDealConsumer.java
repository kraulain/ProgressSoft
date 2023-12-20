package com.progresssoft.deals.service;

import com.progresssoft.deals.model.FXDeal;
import com.progresssoft.deals.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FXDealConsumer {

    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
    public void listen(FXDeal deal) {
        log.info("Received message: " + deal.toString());

    }
}
