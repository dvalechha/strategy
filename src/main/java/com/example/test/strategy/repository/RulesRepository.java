package com.example.test.strategy.repository;

import com.example.test.strategy.model.Rules;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RulesRepository extends MongoRepository<Rules, String> {
}
