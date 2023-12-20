package com.progresssoft.deals.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.progresssoft.deals.model.FXDeal;
import lombok.extern.slf4j.Slf4j;

import javax.money.Monetary;
import java.sql.Timestamp;

@Slf4j
public class FXDealDeserializer extends StdDeserializer<FXDeal> {

    public FXDealDeserializer() {
        this(null);
    }

    public FXDealDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public FXDeal deserialize(final JsonParser jsonParser, final DeserializationContext context) {
        try {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            return FXDeal.builder()
                    .id(node.get("id").intValue())
                    .fromCurrency(Monetary.getCurrency(node.get("from_currency").asText()))
                    .toCurrency(Monetary.getCurrency(node.get("to_currency").asText()))
                    .timestamp(new Timestamp(node.get("created_at").longValue()))
                    .amount(node.get("amount").longValue())
                    .build();
        } catch (Exception e) {
            log.error("Failed to parse fxdeal. " + e.getMessage());
            return null;
        }
    }
}
