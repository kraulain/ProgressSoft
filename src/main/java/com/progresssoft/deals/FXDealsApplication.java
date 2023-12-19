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
import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class FXDealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FXDealsApplication.class, args);
	}

}
