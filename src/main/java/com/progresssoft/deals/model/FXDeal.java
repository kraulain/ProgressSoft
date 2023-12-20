package com.progresssoft.deals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.progresssoft.deals.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.sql.Timestamp;

@Data
@Document(AppConstants.MONGO_COLLECTION)
@AllArgsConstructor
public class FXDeal {
    @Id
    @NotBlank(message = "id is mandatory")
    @JsonProperty("id")
    private int id;

    @NotBlank(message = "From currency is mandatory")
    @JsonProperty("from_currency")
    private CurrencyUnit fromCurrency;

    @NotBlank(message = "To currency is mandatory")
    @JsonProperty("to_currency")
    private CurrencyUnit toCurrency;

    @JsonProperty("created_at")
    private Timestamp timestamp;

    @NotBlank(message = "Amount is mandatory")
    @JsonProperty("amount")
    private MonetaryAmount amount;

}
