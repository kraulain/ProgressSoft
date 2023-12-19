package com.progresssoft.deals;

import com.progresssoft.deals.model.FXDeal;
import com.progresssoft.deals.repository.FXDealRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class FXDealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FXDealsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (FXDealRepository repo){
		return args -> {
			CurrencyUnit from = Monetary.getCurrency("USD");
			CurrencyUnit to = Monetary.getCurrency("XAF");
			MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
					.setCurrency("USD").setNumber(1).create();
			FXDeal deal = new FXDeal(
					UUID.fromString("d8c4c861-5f49-4c74-afcf-ee3a8f820bcc"),
					from,
					to,
					Timestamp.from(Instant.now()),
					oneDolar);

			repo.insert(deal);
		};
	}

}
