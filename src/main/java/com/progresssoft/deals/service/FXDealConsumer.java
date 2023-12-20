package com.progresssoft.deals.service;

import com.progresssoft.deals.model.FXDeal;
import com.progresssoft.deals.repository.FXDealRepository;
import com.progresssoft.deals.util.AppConstants;
import com.progresssoft.deals.util.CustomDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FXDealConsumer {

    @Autowired
    FXDealRepository fxDealRepository;
    @Autowired
    CustomDeserializer customDeserializer;

    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
    public void listen(String message) {
        try {
            FXDeal deal = customDeserializer.messageToDeal(message);
            log.info("New fxdeal with id: " + deal.getId());
            fxDealRepository.save(deal);
            log.info("fxdeal with id: " + deal.getId() + "Saved to mongodb" );
        } catch (Exception e) {
            log.error("Something went wrong when trying to save deal to mongodb. " + e.getMessage());
        }

    }
}
