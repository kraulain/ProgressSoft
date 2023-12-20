package com.progresssoft.deals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.progresssoft.deals.util.AppConstants;
import com.progresssoft.deals.util.FXDealDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.money.CurrencyUnit;
import java.sql.Timestamp;

@Data
@Builder
@Document(AppConstants.MONGO_COLLECTION)
@AllArgsConstructor
@JsonDeserialize(using = FXDealDeserializer.class)
public class FXDeal {
    @Id
    @NotBlank(message = "id is mandatory")
    private int id;

    @NotBlank(message = "From currency is mandatory")
    private CurrencyUnit fromCurrency;

    @NotBlank(message = "To currency is mandatory")
    private CurrencyUnit toCurrency;

    private Timestamp timestamp;

    @NotBlank(message = "Amount is mandatory")
    @Size(min = 100, max = 10000)
    private Long amount;

}
