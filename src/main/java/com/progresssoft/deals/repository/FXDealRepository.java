package com.progresssoft.deals.repository;

import com.progresssoft.deals.model.FXDeal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FXDealRepository extends MongoRepository<FXDeal, UUID> {
}
