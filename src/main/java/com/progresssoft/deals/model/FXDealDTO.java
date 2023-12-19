package com.progresssoft.deals.model;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.sql.Timestamp;
import java.util.UUID;

public record FXDealDTO(UUID id, CurrencyUnit fromCurrency, CurrencyUnit toCurrency, Timestamp timestamp, MonetaryAmount amount) {}
