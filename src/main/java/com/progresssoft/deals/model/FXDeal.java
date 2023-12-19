package com.progresssoft.deals.model;

import com.progresssoft.deals.util.AppConstants;
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
    private int id;
    private CurrencyUnit fromCurrency;
    private CurrencyUnit toCurrency;
    private Timestamp timestamp;
    private MonetaryAmount amount;

}
