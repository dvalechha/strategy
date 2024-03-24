package com.example.test.strategy.repository;

import com.example.test.strategy.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    ClientProfile findByClientCardNumber(String clientCard);
}
