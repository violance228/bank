package com.bank.bank.service;

import com.bank.bank.data.dto.TransferMoneyDto;
import com.bank.bank.data.entity.Card;

public interface TransferService {
    Card doTransfer(TransferMoneyDto addAmount);
    Card doReplenishment(TransferMoneyDto addAmount);
    Card doWithdrawal(TransferMoneyDto addAmount);
}
