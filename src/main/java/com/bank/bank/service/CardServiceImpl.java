package com.bank.bank.service;

import com.bank.bank.data.dto.TransferMoneyDto;
import com.bank.bank.data.entity.Card;
import com.bank.bank.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bank.bank.ExceptionHelper.CARD_NOT_FOUND;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final TransferService transferService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Card save(Card card) {
        return getByNumber(card.getNumber()).orElseGet(() -> {
            card.setPinCode(passwordEncoder.encode(card.getPinCode()));
            return cardRepository.save(card);
        });
    }

    @Override
    @Transactional
    public Card edit(Card card) {
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public Card getById(Long id) {
        return cardRepository.getById(id);
    }

    @Override
    public Optional<Card> getByNumber(String number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    @Transactional
    public Optional<Card> updateBalance(TransferMoneyDto addAmount) {
        switch (addAmount.getOperationType()) {
            case WITHDRAWAL:
                return Optional.of(transferService.doWithdrawal(addAmount));
            case REPLENISHMENT:
                return Optional.of(transferService.doReplenishment(addAmount));
            case TRANSFER_BETWEEN_ACCOUNTS:
                return Optional.of(transferService.doTransfer(addAmount));
            default: return Optional.empty();
        }
    }

    @Override
    public Card getByNumberAndPinCode(String number, String pinCode) {
        Optional<Card> byNumber = getByNumber(number);

        return byNumber.filter(val-> passwordEncoder.matches(pinCode, val.getPinCode())).orElseThrow(CARD_NOT_FOUND);
    }
}
