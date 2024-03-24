package com.example.test.strategy.repository;

import com.example.test.strategy.model.AccountPartnerMap;
import com.example.test.strategy.model.ClientAccount;
import com.example.test.strategy.model.PartnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPartnerMapRepository extends JpaRepository<AccountPartnerMap, Long> {
    AccountPartnerMap findByPartnerMemberAndClientAccount(PartnerMember newAccountPartnerMap, ClientAccount existingClientAccount);
}
