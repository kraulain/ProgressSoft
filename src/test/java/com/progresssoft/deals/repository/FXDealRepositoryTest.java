package com.progresssoft.deals.repository;

import com.progresssoft.deals.model.FXDeal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.money.Monetary;
import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
class FXDealRepositoryTest {

    @Autowired
    FXDealRepository fxDealRepository;

    @Test
    public void givenDealExists_whenFindByDealId_thenGetUser() {
        FXDeal fxDeal = new FXDeal(1,
                Monetary.getCurrency("GBP"),
                Monetary.getCurrency("JPY"),
                new Timestamp(45637829),
                2346L);

        fxDealRepository.save(fxDeal);

        Optional<FXDeal> fxDeal1 = fxDealRepository.findById(1);
        assertTrue(fxDeal1.isPresent());
        assertThat(fxDeal1.get().getId()).isEqualTo(1);
        assertThat(fxDeal1.get().getAmount()).isEqualTo(2346L);
    }

    @Test
    public void givenNonExistingDeal_whenFindById_thenReturnNotPresent(){
        Optional<FXDeal> deal = fxDealRepository.findById(2);
        assertTrue(deal.isEmpty());
    }
}
