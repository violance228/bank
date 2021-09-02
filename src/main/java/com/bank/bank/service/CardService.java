package com.bank.bank.service;

import com.bank.bank.data.dto.TransferMoneyDto;
import com.bank.bank.data.entity.Card;

import java.util.Optional;

public interface CardService {
    Card save(Card card);
    Card edit(Card card);
    Card getById(Long id);
    Optional<Card> getByNumber(String number);
    Optional<Card> updateBalance(TransferMoneyDto addAmount);
    Card getByNumberAndPinCode(String number, String pinCode);
}
