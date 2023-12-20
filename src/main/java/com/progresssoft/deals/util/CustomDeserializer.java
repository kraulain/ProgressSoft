package com.progresssoft.deals.util;

import com.progresssoft.deals.model.FXDeal;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import java.sql.Timestamp;
import java.util.Optional;
@Slf4j
@Component
public class CustomDeserializer {

    /**
     * Performs Custom Deserialization from JSON string to FXDeal
     * Ideal for handling Currency types
     * @param message
     * @return Optional<FXDeal>
     */
    public Optional<FXDeal> messageToDeal(String message){
        try {
            JSONObject messageJson = new JSONObject(message);
            JSONObject dealJson = messageJson.getJSONObject("payload").getJSONObject("after");
            return Optional.ofNullable(FXDeal.builder()
                    .id(dealJson.getInt("id"))
                    .fromCurrency(Monetary.getCurrency(dealJson.getString("from_currency")))
                    .toCurrency(Monetary.getCurrency(dealJson.getString("to_currency")))
                    .timestamp(new Timestamp(dealJson.getLong("created_at")))
                    .amount(dealJson.getLong("amount"))
                    .build());
        } catch (Exception e){
            log.error("Invalid JSON message format: " + e.getMessage());
            return Optional.empty();
        }
    }
}
