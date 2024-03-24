package com.example.test.strategy.repository;

import com.example.test.strategy.model.PartnerMaster;
import com.example.test.strategy.model.PartnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Long> {
    PartnerMember findByPartnerLoyaltyIdAndPartnerMaster(String partnerLoyaltyId, PartnerMaster partnerMaster);
    // You can add custom query methods here if needed
}
