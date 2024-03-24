package com.example.test.strategy.repository;

import com.example.test.strategy.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
    ClientAccount findByPaymentCardNumber(String clientCard);
}
