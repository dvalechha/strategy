package com.example.test.strategy.repository;

import com.example.test.strategy.model.PartnerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerMasterRepository extends JpaRepository<PartnerMaster, Long> {
    PartnerMaster findByPartnerCode(String partnerCode);
}

