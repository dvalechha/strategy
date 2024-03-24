package com.example.test.strategy.repository;

import com.example.test.strategy.model.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {
}
