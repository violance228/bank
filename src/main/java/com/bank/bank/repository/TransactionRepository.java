package com.bank.bank.repository;

import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCard(Card cardId);
}
