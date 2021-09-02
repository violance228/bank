package com.bank.bank.service;

import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    List<Transaction> getAllByCard(Card card);
}
