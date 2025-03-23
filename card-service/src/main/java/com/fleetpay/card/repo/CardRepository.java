package com.fleetpay.card.repo;

import com.fleetpay.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByNumber(String number);
}
