package com.progresssoft.deals.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.sql.Timestamp;

@Data
@Document("fxdeals")
@AllArgsConstructor
public class FXDeal {
    @Id
    private int id;
    private CurrencyUnit fromCurrency;
    private CurrencyUnit toCurrency;
    private Timestamp timestamp;
    private MonetaryAmount amount;

}
