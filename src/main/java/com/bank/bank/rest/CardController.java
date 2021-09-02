package com.bank.bank.rest;

import com.bank.bank.OperationType;
import com.bank.bank.data.dto.CardDto;
import com.bank.bank.data.dto.TransactionDto;
import com.bank.bank.data.dto.TransferMoneyDto;
import com.bank.bank.data.entity.Card;
import com.bank.bank.service.CardService;
import com.bank.bank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bank.bank.ExceptionHelper.CARD_NOT_FOUND;
import static com.bank.bank.ExceptionHelper.TRANSACTION_NOT_FOUND;
import static com.bank.bank.SecurityUtils.getCurrentCardNumber;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/card")
public class CardController {
    private final CardService cardService;
    private final TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<CardDto> get() {
        return ResponseEntity.ok(cardService.getByNumber(getCurrentCardNumber()).map(CardDto::toCardDto)
                .orElseThrow(CARD_NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<CardDto> putBalance(@RequestBody TransferMoneyDto cardDto) {
        return ResponseEntity.ok(cardService.updateBalance(cardDto).map(CardDto::toCardDto).orElseThrow(TRANSACTION_NOT_FOUND));
    }

    @GetMapping("/transaction")
    public ResponseEntity<TransactionDto> getTransactions() {
        Card card = cardService.getByNumber(getCurrentCardNumber()).orElseThrow(CARD_NOT_FOUND);
        return ResponseEntity.ok(TransactionDto.toTransactionDto(transactionService.getAllByCard(card), card));
    }

    @GetMapping("/availableOperationType")
    public ResponseEntity<OperationType[]> availableOperationType() {
        return ResponseEntity.ok(OperationType.values());
    }
}
