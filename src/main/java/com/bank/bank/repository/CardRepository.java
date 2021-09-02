package com.bank.bank.repository;

import com.bank.bank.data.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByNumber(String number);
}
