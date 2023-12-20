package com.progresssoft.deals.service;

import com.progresssoft.deals.model.FXDeal;
import com.progresssoft.deals.repository.FXDealRepository;
import com.progresssoft.deals.util.AppConstants;
import com.progresssoft.deals.util.CustomDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class FXDealConsumer {

    @Autowired
    FXDealRepository fxDealRepository;
    @Autowired
    CustomDeserializer customDeserializer;

    /**
     * Listen to messages on specified kafka topic and Deserialize them before saving to mongodb
     * @param message
     */
    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
    public void listen(String message) {
        try {
            Optional<FXDeal> deal = customDeserializer.messageToDeal(message);
            if(deal.isPresent()){
                log.info("New fxdeal with id: " + deal.get().getId());
                fxDealRepository.save(deal.get());
                log.info("fxdeal with id: " + deal.get().getId() + "Saved to mongodb" );
            }

        } catch (Exception e) {
            log.error("Something went wrong when trying to save deal to mongodb. " + e.getMessage());
        }

    }
}
