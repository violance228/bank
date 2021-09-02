package com.bank.bank.service;

import com.bank.bank.OperationType;
import com.bank.bank.data.dto.TransferMoneyDto;
import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.Transaction;
import com.bank.bank.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bank.bank.ExceptionHelper.CARD_NOT_FOUND;
import static com.bank.bank.ExceptionHelper.CARD_NOT_FOUND_OR_NOT_ENOUGH_MONEY;
import static com.bank.bank.SecurityUtils.getCurrentCardNumber;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final CardRepository cardRepository;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public Card doTransfer(TransferMoneyDto addAmount) {
        Card card = cardRepository.findByNumber(addAmount.getToNumber()).orElseThrow(CARD_NOT_FOUND);

        Card transferFromCard = cardRepository.findByNumber(getCurrentCardNumber()).filter(val-> val.getBalance().compareTo(addAmount.getAmount()) >= 0)
                .orElseThrow(CARD_NOT_FOUND_OR_NOT_ENOUGH_MONEY);
        Transaction transaction1 = transactionService.save(new Transaction(null, OperationType.TRANSFER_BETWEEN_ACCOUNTS.name(), transferFromCard.getNumber(), card.getBalance(), card.getBalance().add(addAmount.getAmount()), card));
        Transaction transaction2 = transactionService.save(new Transaction(null, OperationType.TRANSFER_BETWEEN_ACCOUNTS.name(), card.getNumber(), transferFromCard.getBalance(), transferFromCard.getBalance().subtract(addAmount.getAmount()), transferFromCard));

        card.setBalance(transaction1.getBalanceChangeTo());
        transferFromCard.setBalance(transaction2.getBalanceChangeTo());
        cardRepository.saveAndFlush(card);
        cardRepository.saveAndFlush(transferFromCard);

        return transferFromCard;
    }

    @Override
    @Transactional
    public Card doReplenishment(TransferMoneyDto addAmount) {
        Card card = cardRepository.findByNumber(getCurrentCardNumber()).filter(val-> val.getBalance().compareTo(addAmount.getAmount()) >= 0).orElseThrow(CARD_NOT_FOUND_OR_NOT_ENOUGH_MONEY);

        Transaction transaction = transactionService.save(new Transaction(null, OperationType.REPLENISHMENT.name(), "box", card.getBalance(), card.getBalance().subtract(addAmount.getAmount()), card));
        card.setBalance(transaction.getBalanceChangeTo());
        cardRepository.saveAndFlush(card);

        return card;
    }

    @Override
    @Transactional
    public Card doWithdrawal(TransferMoneyDto addAmount) {
        Card card = cardRepository.findByNumber(addAmount.getToNumber()).orElseThrow(CARD_NOT_FOUND);

        Transaction transaction = transactionService.save(new Transaction(null, OperationType.WITHDRAWAL.name(), "box", card.getBalance(), card.getBalance().add(addAmount.getAmount()), card));
        card.setBalance(transaction.getBalanceChangeTo());
        cardRepository.saveAndFlush(card);

        return card;
    }
}
