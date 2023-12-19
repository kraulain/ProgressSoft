package com.progresssoft.deals.repository;

import com.progresssoft.deals.model.FXDeal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FXDealRepository extends MongoRepository<FXDeal, Integer> {
}
