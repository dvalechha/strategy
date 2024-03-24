package com.example.test.strategy.repository;

import com.example.test.strategy.model.PartnerProductMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerProductMapRepository extends JpaRepository<PartnerProductMap, Integer> {
    // You can add custom query methods here if needed
}
