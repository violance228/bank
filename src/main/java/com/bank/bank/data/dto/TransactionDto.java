package com.bank.bank.data.dto;

import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransactionDto {
    private CardDto cardDto;
    private List<Data> data = new ArrayList<>();

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private Long id;
        private String type;
        private String interaction;
        private BigDecimal balanceChangeFrom;
        private BigDecimal balanceChangeTo;
    }

    public static TransactionDto toTransactionDto(List<Transaction> transaction, Card card) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setCardDto(CardDto.toCardDto(card));
        List<Data> collect = transaction.stream()
                .map(val -> new Data(val.getId(), val.getType(), val.getInteraction(), val.getBalanceChangeFrom(), val.getBalanceChangeTo()))
                .collect(Collectors.toList());
        transactionDto.setData(collect);

        return transactionDto;
    }
}
