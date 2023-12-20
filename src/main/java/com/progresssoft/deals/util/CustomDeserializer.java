package com.progresssoft.deals.util;

import com.progresssoft.deals.model.FXDeal;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import java.sql.Timestamp;

@Component
public class CustomDeserializer {

    public FXDeal messageToDeal(String message){
        JSONObject messageJson = new JSONObject(message);
        JSONObject dealJson = messageJson.getJSONObject("payload").getJSONObject("after");
        return FXDeal.builder()
                .id(dealJson.getInt("id"))
                .fromCurrency(Monetary.getCurrency(dealJson.getString("from_currency")))
                .toCurrency(Monetary.getCurrency(dealJson.getString("to_currency")))
                .timestamp(new Timestamp(dealJson.getLong("created_at")))
                .amount(dealJson.getLong("amount"))
                .build();
    }
}
