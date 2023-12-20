package com.progresssoft.deals.service;

import com.progresssoft.deals.model.FXDeal;
import com.progresssoft.deals.repository.FXDealRepository;
import com.progresssoft.deals.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FXDealConsumer {

    @Autowired
    FXDealRepository fxDealRepository;

    @KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID,
            containerFactory = "fxDealListenerContainerFactory")
    public void listen(FXDeal deal) {
        log.info("New deal with ID: " + deal.getId());
        try {
            fxDealRepository.save(deal);
        } catch (Exception e) {
            log.error("Something went wrong when trying to save deal to mongodb. " + e.getMessage());
        }

    }
}
