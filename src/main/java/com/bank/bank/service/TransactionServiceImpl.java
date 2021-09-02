package com.bank.bank.service;

import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.Transaction;
import com.bank.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bank.bank.ExceptionHelper.FORBIDDEN_EDIT_TRANSACTION;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(Optional.of(transaction).filter(val-> val.getId() == null)
                .orElseThrow(FORBIDDEN_EDIT_TRANSACTION));
    }

    @Override
    public List<Transaction> getAllByCard(Card card) {
        return transactionRepository.findAllByCard(card);
    }
}
